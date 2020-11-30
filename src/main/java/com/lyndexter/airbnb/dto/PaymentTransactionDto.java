package com.lyndexter.airbnb.dto;

import com.lyndexter.airbnb.controller.implementation.ApartamentReservedController;
import com.lyndexter.airbnb.controller.implementation.LessorController;
import com.lyndexter.airbnb.controller.implementation.PaymentTransactionController;
import com.lyndexter.airbnb.controller.implementation.RenterController;
import com.lyndexter.airbnb.model.PaymentTransaction;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PaymentTransactionDto extends ResourceSupport {
  private final PaymentTransaction paymentTransaction;

  public PaymentTransactionDto(PaymentTransaction paymentTransaction) {
    this.paymentTransaction = paymentTransaction;

    add(
        linkTo(
                methodOn(PaymentTransactionController.class)
                    .getPaymentTransaction(paymentTransaction.getId()))
            .withSelfRel());

    add(
        linkTo(
                methodOn(ApartamentReservedController.class)
                    .getApartamentsReservedByPaymentTransaction(paymentTransaction.getId()))
            .withRel("apartamentsReserved"));

    add(
        linkTo(methodOn(LessorController.class).getLessor(paymentTransaction.getLessor().getId()))
            .withRel("lessor"));
    add(
        linkTo(methodOn(RenterController.class).getRenter(paymentTransaction.getRenter().getId()))
            .withRel("renter"));
  }

  public Integer getPaymentTransactionId() {
    return paymentTransaction.getId();
  }

  public Byte getRenterPayment() {
    return paymentTransaction.getRenterPayment();
  }

  public Byte getLessorRecieveMoney() {
    return paymentTransaction.getLessorRecieveMoney();
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

    PaymentTransactionDto that = (PaymentTransactionDto) o;

    return paymentTransaction != null
        ? paymentTransaction.equals(that.paymentTransaction)
        : that.paymentTransaction == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (paymentTransaction != null ? paymentTransaction.hashCode() : 0);
    return result;
  }
}
