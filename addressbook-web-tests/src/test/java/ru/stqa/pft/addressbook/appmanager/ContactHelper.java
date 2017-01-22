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
import java.util.Arrays;
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
      attach(By.name("photo"), contactData.getPhoto());
      //type(By.name("nickname"), contactData.getNickname());
      //type(By.name("company"), contactData.getCompany());
      //type(By.name("notes"), contactData.getNotes());

      if (creation) {
         if (contactData.getGroup() != null) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
         }
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

   public void details(int id) {
      click(By.xpath("//a[@href='view.php?id=" + id + "']"));
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
         String allPhones = cells.get(5).getText();
         String allEmails = cells.get(4).getText();
         String address = cells.get(3).getText();
         String[] phones = cells.get(5).getText().split("\n");
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName)
                 .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
      }
      return contacts;

   }

   public ContactData infoFromEditForm(ContactData contact) {
      initContactModificationById(contact.getId());
      String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      String email = wd.findElement(By.name("email")).getAttribute("value");
      String email2 = wd.findElement(By.name("email2")).getAttribute("value");
      String email3 = wd.findElement(By.name("email3")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      wd.navigate().back();
      return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
              .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
   }

   public String getDetailsData () {
      return wd.findElement(By.cssSelector("div[id='content']")).getText();
   }


}
