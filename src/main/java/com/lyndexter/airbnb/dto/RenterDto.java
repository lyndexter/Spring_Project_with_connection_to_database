package com.lyndexter.airbnb.dto;

import com.lyndexter.airbnb.controller.implementation.PaymentTransactionController;
import com.lyndexter.airbnb.controller.implementation.PhotoController;
import com.lyndexter.airbnb.controller.implementation.RenterController;
import com.lyndexter.airbnb.model.Renter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RenterDto extends ResourceSupport {
  private final Renter renter;

  public RenterDto(Renter renter) {
    this.renter = renter;

    add(linkTo(methodOn(RenterController.class).getRenter(renter.getId())).withSelfRel());

    add(
        linkTo(methodOn(PhotoController.class).getPhoto(renter.getPhoto().getId()))
            .withRel("photo"));

    add(
        linkTo(
                methodOn(PaymentTransactionController.class)
                    .getPaymentTransactionByRenter(renter.getId()))
            .withRel("paymentTransactions"));
  }

  public Integer getLessorId() {
    return renter.getId();
  }

  public String getName() {
    return renter.getName();
  }

  public String getSurname() {
    return renter.getSurname();
  }

  public String getLastName() {
    return renter.getLastName();
  }

  public String getPhoneNumber() {
    return renter.getPhoneNumber();
  }

  public String getCardNumber() {
    return renter.getCardNumber();
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

    RenterDto renterDto = (RenterDto) o;

    return renter != null ? renter.equals(renterDto.renter) : renterDto.renter == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (renter != null ? renter.hashCode() : 0);
    return result;
  }
}
