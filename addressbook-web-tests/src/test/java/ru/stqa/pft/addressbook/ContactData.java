package ru.stqa.pft.addressbook;

public class ContactData {
   private final String firstName;
   private final String lastName;
   private final String nickname;
   private final String company;
   private final String notes;

   public ContactData(String firstName, String lastName, String nickname, String company, String notes) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.nickname = nickname;
      this.company = company;
      this.notes = notes;
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
}
