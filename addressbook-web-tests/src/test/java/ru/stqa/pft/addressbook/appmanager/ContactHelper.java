package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Lex on 15.12.2016.
 */
public class ContactHelper extends HelperBase {
   public ContactHelper(FirefoxDriver wd) {
      super(wd);
   }
   public void initContactCreation() {
      click(By.linkText("add new"));
   }
   public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("nickname"), contactData.getNickname());
      type(By.name("company"), contactData.getCompany());
      type(By.name("notes"), contactData.getNotes());
   }
   public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }
   public void returnToHomePage() {
      click(By.linkText("home page"));
   }

   public void selectContact() {
      click(By.name("selected[]"));
   }

   public void deleteSelectedContacts() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public void initContactModification() {
      click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }
}
