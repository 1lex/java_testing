package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Lex on 08.12.2016.
 */
public class NavigationHelper extends HelperBase{

   public NavigationHelper(FirefoxDriver wd) {
      super(wd);
   }

   public void gotoGroupPage() {
      click(By.linkText("groups"));
   }
}