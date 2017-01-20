package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


   @Test
   public void testContactCreation() {
      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contact = new ContactData("testName1", "testName2", "testNickName", "Apple", "123", "test1");
      app.getContactHelper().createContact(contact);
      app.goTo().goToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();

      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);
   }


}
