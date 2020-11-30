package com.lyndexter.airbnb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "apartament_reserved")
public class ApartamentReserved {
  private Integer id;
  private Byte reserved;
  private String wish;
  private Apartament apartament;
  private PaymentTransaction paymentTransaction;

  public static void printHeaders() {
    System.out.format(
        "%3s    %-12s %-10s %-10s %-40s %n",
        "id", "apartament", "reserved", "paymentTransaction", "wish");
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

  @Column(name = "reserved")
  public Byte getReserved() {
    return reserved;
  }

  public void setReserved(Byte reserved) {
    this.reserved = reserved;
  }

  @Column(name = "wish")
  public String getWish() {
    return wish;
  }

  public void setWish(String wish) {
    this.wish = wish;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ApartamentReserved that = (ApartamentReserved) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (reserved != null ? !reserved.equals(that.reserved) : that.reserved != null) {
      return false;
    }
    return wish != null ? wish.equals(that.wish) : that.wish == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (reserved != null ? reserved.hashCode() : 0);
    result = 31 * result + (wish != null ? wish.hashCode() : 0);
    return result;
  }

  @ManyToOne
  @JoinColumn(name = "apartament_id", referencedColumnName = "id", nullable = false)
  public Apartament getApartament() {
    return apartament;
  }

  public void setApartament(Apartament apartament) {
    this.apartament = apartament;
  }

  @ManyToOne
  @JoinColumn(name = "payment_transaction_id", referencedColumnName = "id", nullable = false)
  public PaymentTransaction getPaymentTransaction() {
    return paymentTransaction;
  }

  public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
    this.paymentTransaction = paymentTransaction;
  }

  @Override
  public String toString() {
    return String.format(
        "%3s    %-12s %-10s %-10s %-40s %n",
        id, apartament.getId(), reserved, paymentTransaction.getId(), wish);
  }
}
