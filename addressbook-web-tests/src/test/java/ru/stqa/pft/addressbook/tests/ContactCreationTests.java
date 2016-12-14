package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    


    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("testName1", "testName2", "testNickName", "Apple", "123"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }




}
