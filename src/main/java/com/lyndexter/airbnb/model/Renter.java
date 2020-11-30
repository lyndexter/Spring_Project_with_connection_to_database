package com.lyndexter.airbnb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Renter {
  private Integer id;
  private String name;
  private String surname;
  private String lastName;
  private String phoneNumber;
  private String cardNumber;
  private Set<PaymentTransaction> paymentTransactions;
  private Photo photo;

  public static void printHeaders() {
    System.out.format(
        "%3s    %-22s %-22s %-22s %-18s %-19s %-5s%n",
        "id", "name", "surname", "lastName", "phoneNumber", "cardNumber", "photo");
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Renter renter = (Renter) o;

    return Objects.equals(id, renter.id);
  }

  @Override
  public int hashCode() {

    return id != null ? id.hashCode() : 0;
  }

  @OneToMany(mappedBy = "renter")
  public Set<PaymentTransaction> getPaymentTransactions() {
    return paymentTransactions;
  }

  public void setPaymentTransactions(Set<PaymentTransaction> paymentTransactions) {
    this.paymentTransactions = paymentTransactions;
  }

  @ManyToOne
  @JoinColumn(name = "photo_id", referencedColumnName = "id", nullable = false)
  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }

  @Override
  public String toString() {
    return String.format(
        "%3s    %-22s %-22s %-22s %-18s %-19s %-5s%n",
        id, name, surname, lastName, phoneNumber, cardNumber, photo.getId());
  }
}
