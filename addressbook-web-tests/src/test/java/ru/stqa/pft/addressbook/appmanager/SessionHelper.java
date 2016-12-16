package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Lex on 08.12.2016.
 */
public class SessionHelper extends HelperBase {


   public SessionHelper(WebDriver wd) {
      super(wd);
   }

   public void login(String username, String password) {
      type(By.name("user"), username);
      type(By.name("pass"), password);
      click(By.xpath("//form[@id='LoginForm']/input[3]"));
   }

   public void returnHome() {
      click(By.linkText("home"));
   }
}
