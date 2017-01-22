package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


   @Test
   public void testContactCreation() {
      Contacts before = app.contact().all();
      //ContactData contact = new ContactData("testName1", "testName2", "testNickName", "Apple", "123", "test1");
      File photo = new File("src/test/resources/1.jpg");
      ContactData contact = new ContactData().withFirstName("testName1").withLastName("testName2").withPhoto(photo);
      app.contact().create(contact);
      app.goTo().homePage();
      Contacts after = app.contact().all();
      assertThat(after.size(), equalTo(before.size()+1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

   }
}



