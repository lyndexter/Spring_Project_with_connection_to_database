package com.lyndexter.airbnb.dto;

import com.lyndexter.airbnb.controller.implementation.ApartamentController;
import com.lyndexter.airbnb.controller.implementation.ApartamentReservedController;
import com.lyndexter.airbnb.controller.implementation.PaymentTransactionController;
import com.lyndexter.airbnb.model.ApartamentReserved;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ApartamentReservedDto extends ResourceSupport {
  private final ApartamentReserved apartamentReserved;

  public ApartamentReservedDto(ApartamentReserved apartamentReserved) {
    this.apartamentReserved = apartamentReserved;

    add(
        linkTo(
                methodOn(ApartamentReservedController.class)
                    .getApartamentReserved(apartamentReserved.getId()))
            .withSelfRel());

    add(
        linkTo(
                methodOn(ApartamentController.class)
                    .getApartament(apartamentReserved.getApartament().getId()))
            .withRel("apartament"));

    add(
        linkTo(
                methodOn(PaymentTransactionController.class)
                    .getPaymentTransaction(apartamentReserved.getPaymentTransaction().getId()))
            .withRel("paymentTransaction"));
  }

  public Integer getApartamentReservedId() {
    return apartamentReserved.getId();
  }

  public Byte getReserved() {
    return apartamentReserved.getReserved();
  }

  public String getWish() {
    return apartamentReserved.getWish();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    ApartamentReservedDto that = (ApartamentReservedDto) o;

    return apartamentReserved != null
        ? apartamentReserved.equals(that.apartamentReserved)
        : that.apartamentReserved == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (apartamentReserved != null ? apartamentReserved.hashCode() : 0);
    return result;
  }
}
