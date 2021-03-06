package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validContactsFromJson() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
      String json = "";
      String line = reader.readLine();
      while (line != null){
         json += line;
         line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
   }


   @Test(dataProvider = "validContactsFromJson")
   public void testContactCreation(ContactData contact) {
      Groups groups = app.db().groups();
      app.goTo().homePage();
      Contacts before = app.db().contacts();
      app.contact().create(contact);
      app.goTo().homePage();
      Contacts after = app.db().contacts();
      assertThat(after.size(), equalTo(before.size()+1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

   }
   @Test
   public void testContactCreationWithPhoto() { //photo игнорируется Hibernate'ом
      Contacts before = app.db().contacts();;
      //ContactData contact = new ContactData("testName1", "testName2", "testNickName", "Apple", "123", "test1");
      File photo = new File("src/test/resources/1.jpg");
      ContactData contact = new ContactData().withFirstName("testName1").withLastName("testName2").withPhoto(photo);
      app.contact().create(contact);
      app.goTo().homePage();
      Contacts after = app.db().contacts();;
      assertThat(after.size(), equalTo(before.size()+1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

   }
}



