package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Lex on 15.12.2016.
 */
public class ContactDeletionTests extends TestBase {

   @Test
   public void testContactDeletion(){
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("testName1", "testName2", "testNickName", "Apple", "123", "test1"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size()-1);
      app.getContactHelper().deleteSelectedContacts();
      app.getContactHelper().submitOnWindow();
      app.goTo().goToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();

      before.remove(before.size()-1);
      Assert.assertEquals(before, after);
   }
}
