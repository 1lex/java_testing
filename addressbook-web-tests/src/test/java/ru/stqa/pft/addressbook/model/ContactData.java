package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
   @Id
   @Column(name = "id")
   private int id = Integer.MAX_VALUE;
   @Expose
   @Column(name = "firstname")
   private String firstName;
   @Expose
   @Column(name = "lastname")
   private String lastName;
   @Transient
   private String nickname;
   @Transient
   private String company;
   @Transient
   private String notes;
   @Column(name = "home")
   @Type(type = "text")
   private String homePhone;
   @Column(name = "mobile")
   @Type(type = "text")
   private String mobilePhone;
   @Column(name = "work")
   @Type(type = "text")
   private String workPhone;
   @Column(name = "email")
   @Type(type = "text")
   private String email;
   @Column(name = "email2")
   @Type(type = "text")
   private String email2;
   @Column(name = "email3")
   @Type(type = "text")
   private String email3;
   @Transient
   private String address;
/*   @Column(name = "photo")
   @Type(type = "text")*/
   @Transient
   private File photo;
   @Transient
   private String allPhones;
   @Transient
   private String allEmails;
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "address_in_groups",
           joinColumns = @JoinColumn(name = "id"),
           inverseJoinColumns = @JoinColumn(name = "group_id"))
   private Set<GroupData> groups = new HashSet<GroupData>();

   public File getPhoto() {
      //return new File(photo);
      return photo;
   }

   public String getEmail() {
      return email;
   }

   public String getEmail2() {
      return email2;
   }

   public String getEmail3() {
      return email3;
   }

   public String getAddress() {
      return address;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public String getAllPhones() {
      return allPhones;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
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

   public Groups getGroups() {
      return new Groups(groups);
   }

   public int getId() {
      return id;
   }

   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public ContactData withNickname(String nickname) {
      this.nickname = nickname;
      return this;
   }

   public ContactData withCompany(String company) {
      this.company = company;
      return this;
   }

   public ContactData withNotes(String notes) {
      this.notes = notes;
      return this;
   }



   public ContactData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactData withMobilePhone(String mobilePhone) {
      this.mobilePhone = mobilePhone;
      return this;
   }

   public ContactData withWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
   }

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactData withEmail2(String email2) {
      this.email2 = email2;
      return this;
   }

   public ContactData withEmail3(String email3) {
      this.email3 = email3;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public ContactData withPhoto(File photo) {
      //this.photo = photo.getPath();
      this.photo = photo;
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactData that = (ContactData) o;

      if (id != that.id) return false;
      if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
      return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      return result;
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
