package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Lex on 24.01.2017.
 */
public class ContactToGroupTest extends TestBase {

   @BeforeMethod
   public void ensureContactPreconditions() {
      if (app.db().contacts().size() == 0) {
         app.goTo().homePage();
         app.contact().create(new ContactData().withFirstName("testName11"));
      }
   }

   @BeforeMethod
   public void ensureGroupPreconditions() {
      if (app.db().groups().size() == 0) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("test1"));
      }
   }

   @BeforeMethod
   public void ensureContactInGroupPreconditions() {

   }

/*   @Test
   public void testContactToGroup() {
      app.goTo().homePage();
      Contacts contactsBefore = app.db().contacts();
      Groups contactsGroupsBefore = new Groups();
      for ( ContactData contact :  contactsBefore ) {
         Groups contactGroups = contact.getGroups();
         for (GroupData group : contactGroups) {
            contactsGroupsBefore = contactsGroupsBefore.withAdded(group);
         }
      }
      ContactData movingContact = contactsBefore.iterator().next();
      Groups groups = app.db().groups();
      GroupData selectedGroup = groups.iterator().next();
      app.contact().moveToGroup(movingContact, selectedGroup);
      app.goTo().homePage();




      Contacts contactsAfter = app.db().contacts();
      Groups contactsGroupsAfter = new Groups();
      for ( ContactData contact :  contactsAfter ) {
         Groups contactGroups = contact.getGroups();
         for (GroupData group : contactGroups) {
            contactsGroupsAfter = contactsGroupsAfter.withAdded(group);
         }
      }

      assertThat(contactsGroupsAfter, equalTo(contactsGroupsBefore.withAdded(selectedGroup)));



   }*/

   @Test
   public void testContactToGroup() {
      app.goTo().homePage();
      Contacts contactsList = app.db().contacts(); //получаем список контактов
      ContactData movingContact = contactsList.iterator().next(); //выбираем контакт, который будем перемещать в группу
      Groups movingContactGroups = movingContact.getGroups(); //смотрим, какие группы у него есть
      Groups groups = app.db().groups(); //получаем список всех групп
      GroupData selectedGroup = groups.iterator().next(); //выбираем группу, в которую будем перемещать контакт
      app.contact().moveToGroup(movingContact, selectedGroup); //вызываем функцию перемещения контакта
      app.goTo().homePage();
      Contacts contactsListAfter = app.db().contacts(); //получаем обновленный список контактов
      ContactData movedContact = new ContactData(); //заводим объект, в котором будем хранить перемещенный контакт
      for (ContactData contact : contactsListAfter) { //ищем перемещенный контакт в обновленном списке контактов
         if (contact.equals(movingContact)) {
            movedContact = contact;
         }
      }
      Groups movedContactGroups = movedContact.getGroups(); //смотрим, какие группы у него есть
      assertThat(movedContactGroups, equalTo(movingContactGroups.withAdded(selectedGroup))); //сравниваем новый список групп контакта, со старым с добавленной группой, которая выбиралась в 77 строке



   }


}
