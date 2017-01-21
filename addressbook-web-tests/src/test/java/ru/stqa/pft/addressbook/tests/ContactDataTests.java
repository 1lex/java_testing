package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Lex on 21.01.2017.
 */
public class ContactDataTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData().withFirstName("testName11"));
      }
   }

   @Test
   public void testContactData() {
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
      assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
   }


   private String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> ! s.equals(""))
              .map(ContactDataTests::cleaned)
              .collect(Collectors.joining("\n"));
   }

   private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> ! s.equals(""))
              .collect(Collectors.joining("\n"));
   }


   public static String cleaned(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }

}
