package com.lyndexter.airbnb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "payment_transaction")
public class PaymentTransaction {
  private Integer id;
  private Byte renterPayment;
  private Byte lessorRecieveMoney;
  private Set<ApartamentReserved> apartamentReserveds;
  private Renter renter;
  private Lessor lessor;

  public static void printHeaders() {
    System.out.format(
        "%3s    %-8s %-8s %-10s %-10s%n",
        "id", "renter", "lessor", "renterPayment", "lessorRecieveMoney");
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

  @Column(name = "renter_payment")
  public Byte getRenterPayment() {
    return renterPayment;
  }

  public void setRenterPayment(Byte renterPayment) {
    this.renterPayment = renterPayment;
  }

  @Column(name = "lessor_recieve_money")
  public Byte getLessorRecieveMoney() {
    return lessorRecieveMoney;
  }

  public void setLessorRecieveMoney(Byte lessorRecieveMoney) {
    this.lessorRecieveMoney = lessorRecieveMoney;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentTransaction that = (PaymentTransaction) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return id != null ? id.hashCode() : 0;
  }

  @OneToMany(mappedBy = "paymentTransaction")
  public Set<ApartamentReserved> getApartamentReserveds() {
    return apartamentReserveds;
  }

  public void setApartamentReserveds(Set<ApartamentReserved> apartamentReserveds) {
    this.apartamentReserveds = apartamentReserveds;
  }

  @ManyToOne
  @JoinColumn(name = "renter_id", referencedColumnName = "id", nullable = false)
  public Renter getRenter() {
    return renter;
  }

  public void setRenter(Renter renter) {
    this.renter = renter;
  }

  @ManyToOne
  @JoinColumn(name = "lessor_id", referencedColumnName = "id", nullable = false)
  public Lessor getLessor() {
    return lessor;
  }

  public void setLessor(Lessor lessor) {
    this.lessor = lessor;
  }

  @Override
  public String toString() {
    return String.format(
        "%3s    %-8s %-8s %-10s %-10s%n",
        id, renter.getId(), lessor.getId(), renterPayment, lessorRecieveMoney);
  }
}
