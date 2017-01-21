package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lex on 15.12.2016.
 */
public class ContactHelper extends HelperBase {
   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("nickname"), contactData.getNickname());
      type(By.name("company"), contactData.getCompany());
      type(By.name("notes"), contactData.getNotes());
      if (creation) {
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }


   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   public void selectContactById(int id) {
      wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
   }

   public void deleteSelectedContacts() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public void initContactModification(int index) {
      click(By.xpath("//table[@id='maintable']/tbody/tr[" + (index+1) + "]/td[8]/a/img"));
   }

   public void initContactModificationById(int id) {
      click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

   public void returnToHomePage() {
      click(By.linkText("home page"));
   }

   public void create(ContactData contact) {
      initContactCreation();
      fillContactForm(contact, true);
      submitContactCreation();
      returnToHomePage();
   }

   public void delete(ContactData contact) {
      selectContactById(contact.getId());
      deleteSelectedContacts();
      submitOnWindow();
   }

   public void modify(ContactData contact) {
      selectContactById(contact.getId());
      initContactModificationById(contact.getId());
      fillContactForm(contact, false);
     submitContactModification();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public List<ContactData> list() {

         List<ContactData> contacts = new ArrayList<ContactData>();
         List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
         for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
         }
         return contacts;
   }

   public Contacts all() {
      Contacts contacts = new Contacts();
      List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
      for (WebElement element : elements) {
         List<WebElement> cells = element.findElements(By.tagName("td"));
         String lastName = cells.get(1).getText();
         String firstName = cells.get(2).getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
      }
      return contacts;

   }
}
