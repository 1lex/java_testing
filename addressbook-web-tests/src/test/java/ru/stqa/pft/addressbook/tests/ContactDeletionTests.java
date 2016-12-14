package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Lex on 15.12.2016.
 */
public class ContactDeletionTests extends TestBase {

   @Test
   public void testContactDeletion(){
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteSelectedContacts();
      app.getContactHelper().submitOnWindow();
   }
}
