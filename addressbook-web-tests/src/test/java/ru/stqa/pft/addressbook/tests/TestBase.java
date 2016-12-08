package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicatuinManager;

/**
 * Created by Lex on 07.12.2016.
 */
public class TestBase {

   protected final ApplicatuinManager app = new ApplicatuinManager();

   @BeforeMethod
   public void setUp() throws Exception {
      app.init();
   }

   @AfterMethod
   public void tearDown() {
      app.stop();
   }

}
