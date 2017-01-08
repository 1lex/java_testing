package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    


    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("testName1", "testName2", "testNickName", "Apple", "123", "test1"));
        app.getNavigationHelper().goToHomePage();
    }




}
