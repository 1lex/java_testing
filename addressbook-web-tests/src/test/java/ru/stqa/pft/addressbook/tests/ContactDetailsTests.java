package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Lex on 21.01.2017.
 */
public class ContactDetailsTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData().withFirstName("testName11"));
      }
   }

   @Test
   public void testContactDetails() {
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      String namesAndAddress = String.join("\n", mergeNames(contactInfoFromEditForm),
              mergeAddress(contactInfoFromEditForm));
      String mergedNamesAddressPhones = String.join("\n",namesAndAddress, mergePhones(contactInfoFromEditForm));
      String mergedAll = String.join("\n\n",mergedNamesAddressPhones, mergeEmails(contactInfoFromEditForm));
      app.goTo().homePage();
      app.contact().details(contact.getId());
      assertThat(app.contact().getDetailsData(), equalTo(mergeAll(contactInfoFromEditForm)));
      assertThat(app.contact().getDetailsData(), equalTo(mergedAll));


   }

   private String mergeAll(ContactData contact) {
      String result = "";
      if (!contact.getFirstName().equals("")) {
         result += contact.getFirstName() + " ";
      }
      if (!contact.getLastName().equals("")) {
         result += contact.getLastName();
      }
      if (!contact.getAddress().equals("")) {
         result += "\n" + contact.getAddress();
      }
      result = result + "\n" + "";
      if (!contact.getHomePhone().equals("")) {
         String modifiedHome = "H: " + contact.getHomePhone();
         if (modifiedHome.length() > 3) {
            result += "\n" + modifiedHome;
         }
      }
      if (!contact.getMobilePhone().equals("")) {
         String modifiedMobile = "M: " + contact.getMobilePhone();
         if (modifiedMobile.length() > 3) {
            result += "\n" + modifiedMobile;
         }
      }
      if (!contact.getWorkPhone().equals("")) {
         String modifiedWork = "W: " + contact.getWorkPhone();
         if (modifiedWork.length() > 3) {
            result += "\n" + modifiedWork;
         }
      }
      result = result + "" + "\n";
      if (!contact.getEmail().equals("")) {
         result += "\n" + contact.getEmail();
      }
      if (!contact.getEmail2().equals("")) {
         result += "\n" + contact.getEmail2();
      }
      if (!contact.getEmail3().equals("")) {
         result += "\n" + contact.getEmail3();
      }
      return result;
   }

   private String mergeNames(ContactData contact) {
      return Arrays.asList(contact.getFirstName(), contact.getLastName())
              .stream().filter((s) -> !s.equals(""))
              .collect(Collectors.joining(" "));
   }

   private String mergeAddress(ContactData contact) {
      return Arrays.asList(contact.getAddress())
              .stream().filter((s) -> !s.equals(""))
              .collect(Collectors.joining(""));
   }

   private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> !s.equals(""))
              .collect(Collectors.joining("\n"));
   }
/*
   private String mergePhones(ContactData contact) {
      return Arrays.asList("H: " +contact.getHomePhone(), "M: " +contact.getMobilePhone(), "W: " +contact.getWorkPhone())
              .stream().filter((s) -> ! s.equals(""))
              .collect(Collectors.joining("\n"));
   }*/

   private String mergePhones(ContactData contact) {
      String home = "";
      String mobile = "";
      String work = "";
      if (!contact.getHomePhone().equals("")) {
         home = "\nH: " + contact.getHomePhone();
         if (home.length() == 3) {
            home = null;
         }
      }
      if (!contact.getMobilePhone().equals("")) {
         mobile = "\nM: " + contact.getMobilePhone();
         if (mobile.length() == 3) {
            mobile = null;
         }
      }
      if (!contact.getWorkPhone().equals("")) {
         work = "\nW: " + contact.getWorkPhone();
         if (work.length() == 3) {
            work = null;
         }
      }

/*      String tmp = "\n";
      String home =  Arrays.asList(tmp, contact.getHomePhone())
              .stream().filter((s) -> ! s.equals("") &&  s.equals("\n"))
              .collect(Collectors.joining("H: "));
      String mobile = Arrays.asList(tmp, contact.getMobilePhone())
              .stream().filter((s) -> ! s.equals("") &&  s.equals("\n"))
              .collect(Collectors.joining("M: "));
      String work = Arrays.asList(tmp, contact.getWorkPhone())
              .stream().filter((s) -> ! s.equals("") &&  s.equals("\n"))
              .collect(Collectors.joining("W: "));
      return String.join(mobile, home, work);*/

      return String.join("", home, mobile, work);
   }
}


