package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Lex on 25.01.2017.
 */
public class ContactOutOfGroupTest extends TestBase {
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
      Contacts contactsList = app.db().contacts(); //получаем список контактов
      for (ContactData contact : contactsList) {
         if (contact.getGroups().size() == 0) {
            app.goTo().homePage();
            Groups groups = app.db().groups(); //получаем список всех групп
            GroupData selectedGroup = groups.iterator().next(); //выбираем группу, в которую будем перемещать контакт
            app.contact().moveToGroup(contact, selectedGroup); //вызываем функцию перемещения контакта
         }
      }
   }


   @Test
   public void testContactOutOfGroup() {
      app.goTo().homePage();
      Contacts contactsList = app.db().contacts(); //получаем список контактов
      ContactData movingContact = contactsList.iterator().next(); //выбираем контакт, у которого будем удалять группу
      Groups movingContactGroups = movingContact.getGroups(); //получаем список его групп
      GroupData selectedGroup = movingContactGroups.iterator().next(); //выбираем группу, которую будем удалять
      app.contact().deleteContactFromGroup(movingContact, selectedGroup); //вызываем метод удаления
      Contacts contactsListAfter = app.db().contacts(); //получаем обновлвенный список контактов
      ContactData movedContact = new ContactData(); //заводим объект, в котором будем хранить перемещенный контакт
      for (ContactData contact : contactsListAfter) { //ищем наш контакт в обновленном списке контактов
         if (contact.equals(movingContact)) {
            movedContact = contact;
         }
      }
      app.goTo().homePage();
      Groups movedContactGroups = movedContact.getGroups(); //получаем обновленный список групп нашего контакта
      Groups res = movingContactGroups.without(selectedGroup); //вынес результат "вычитания" группы в отдельный объект, т.к. ассерт падал при подставновке выражения с методом
      assertThat(movedContactGroups, equalTo(res)); //сравниваем списки групп после преобразования и до преобразования с вычитанием элемента

   }
}
