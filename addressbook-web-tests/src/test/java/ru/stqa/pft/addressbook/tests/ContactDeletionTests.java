package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Lex on 15.12.2016.
 */
public class ContactDeletionTests extends TestBase {

   @Test
   public void testContactDeletion(){
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("testName1", "testName2", "testNickName", "Apple", "123", "test1"));
      }
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContacts();
      app.getContactHelper().submitOnWindow();
      app.getNavigationHelper().goToHomePage();
   }
}
