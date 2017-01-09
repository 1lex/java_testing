package ru.stqa.pft.addressbook.model;

public class ContactData {
   private int id;
   private final String firstName;
   private final String lastName;
   private final String nickname;
   private final String company;
   private final String notes;
   private String group;

   public ContactData(int id, String firstName, String lastName, String nickname, String company, String notes, String group) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.nickname = nickname;
      this.company = company;
      this.notes = notes;
      this.group = group;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
      return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

   }

   @Override
   public int hashCode() {
      int result = firstName != null ? firstName.hashCode() : 0;
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      return result;
   }

   public ContactData(String firstName, String lastName, String nickname, String company, String notes, String group) {
      this.id = Integer.MAX_VALUE;
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

   public int getId() {
      return id;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              '}';
   }

}
