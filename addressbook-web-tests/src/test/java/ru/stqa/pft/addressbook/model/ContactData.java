package ru.stqa.pft.addressbook.model;

public class ContactData {
   private final String firstName;
   private final String lastName;
   private final String nickname;
   private final String company;
   private final String notes;
   private String group;

   public ContactData(String firstName, String lastName, String nickname, String company, String notes, String group) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.nickname = nickname;
      this.company = company;
      this.notes = notes;
      this.group = group;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getNickname() {
      return nickname;
   }

   public String getCompany() {
      return company;
   }

   public String getNotes() {
      return notes;
   }

   public String getGroup() {
      return group;
   }
}
