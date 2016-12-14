package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Lex on 15.12.2016.
 */
public class ContactModificationTests extends TestBase {

   @Test
   public void testContactModification() {
      app.getContactHelper().selectContact();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactData("testName1", "testName2", "testNickName", "Apple", "123"));
      app.getContactHelper().submitContactModification();
      app.getContactHelper().returnToHomePage();
   }
}
