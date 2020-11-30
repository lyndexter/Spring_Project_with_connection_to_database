package com.lyndexter.airbnb.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Lessor {
  private Integer id;
  private String name;
  private String surname;
  private String lastName;
  private String phoneNumber;
  private String cardNumber;
  private String contactInfo;
  private Set<Apartament> apartaments;
  private Photo photo;
  private Set<PaymentTransaction> paymentTransactions;

  public static void printHeaders() {
    System.out.format(
        "%3s    %-22s %-22s %-22s %-18s %-19s %-10s %-60s %n",
        "id", "name", "surname", "lastName", "phoneNumber", "cardNumber", "photo", "contactInfo");
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "surname")
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Column(name = "last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "phone_number")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Column(name = "card_number")
  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Column(name = "contact_info")
  public String getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Lessor lessor = (Lessor) o;

    if (id != null ? !id.equals(lessor.id) : lessor.id != null) {
      return false;
    }
    if (name != null ? !name.equals(lessor.name) : lessor.name != null) {
      return false;
    }
    if (surname != null ? !surname.equals(lessor.surname) : lessor.surname != null) {
      return false;
    }
    if (lastName != null ? !lastName.equals(lessor.lastName) : lessor.lastName != null) {
      return false;
    }
    if (phoneNumber != null
        ? !phoneNumber.equals(lessor.phoneNumber)
        : lessor.phoneNumber != null) {
      return false;
    }
    if (cardNumber != null ? !cardNumber.equals(lessor.cardNumber) : lessor.cardNumber != null) {
      return false;
    }
    return contactInfo != null
        ? contactInfo.equals(lessor.contactInfo)
        : lessor.contactInfo == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
    result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
    result = 31 * result + (contactInfo != null ? contactInfo.hashCode() : 0);
    return result;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lessor")
  public Set<Apartament> getApartaments() {
    return apartaments;
  }

  public void setApartaments(Set<Apartament> apartaments) {
    this.apartaments = apartaments;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "photo_id", referencedColumnName = "id", nullable = false)
  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }

  @OneToMany(mappedBy = "lessor", fetch = FetchType.LAZY)
  public Set<PaymentTransaction> getPaymentTransactions() {
    return paymentTransactions;
  }

  public void setPaymentTransactions(Set<PaymentTransaction> paymentTransactions) {
    this.paymentTransactions = paymentTransactions;
  }

  @Override
  public String toString() {
    return String.format(
        "%3s    %-22s %-22s %-22s %-18s %-19s %-10s %-60s %n",
        id, name, surname, lastName, phoneNumber, cardNumber, photo.getId(), contactInfo);
  }
}
