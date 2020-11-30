package com.lyndexter.airbnb.dto;

import com.lyndexter.airbnb.controller.implementation.ApartamentController;
import com.lyndexter.airbnb.controller.implementation.LessorController;
import com.lyndexter.airbnb.controller.implementation.PaymentTransactionController;
import com.lyndexter.airbnb.controller.implementation.PhotoController;
import com.lyndexter.airbnb.model.Lessor;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class LessorDto extends ResourceSupport {
  private final Lessor lessor;

  public LessorDto(Lessor lessor) {
    this.lessor = lessor;

    add(linkTo(methodOn(LessorController.class).getLessor(lessor.getId())).withSelfRel());
    add(
        linkTo(methodOn(ApartamentController.class).getApartamentsByLessor(lessor.getId()))
            .withRel("lessorApartaments"));
    add(
        linkTo(methodOn(PhotoController.class).getPhoto(lessor.getPhoto().getId()))
            .withRel("photo"));

    add(
        linkTo(
                methodOn(PaymentTransactionController.class)
                    .getPaymentTransactionByLessor(lessor.getId()))
            .withRel("paymentTransactions"));
  }

  public Integer getLessorId() {
    return lessor.getId();
  }

  public String getName() {
    return lessor.getName();
  }

  public String getSurname() {
    return lessor.getSurname();
  }

  public String getLastName() {
    return lessor.getLastName();
  }

  public String getPhoneNumber() {
    return lessor.getPhoneNumber();
  }

  public String getCardNumber() {
    return lessor.getCardNumber();
  }

  public String getContactInfo() {
    return lessor.getContactInfo();
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

    LessorDto lessorDto = (LessorDto) o;

    return lessor != null ? lessor.equals(lessorDto.lessor) : lessorDto.lessor == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (lessor != null ? lessor.hashCode() : 0);
    return result;
  }
}
