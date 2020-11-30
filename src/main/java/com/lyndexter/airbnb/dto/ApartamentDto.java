package com.lyndexter.airbnb.dto;

import com.lyndexter.airbnb.controller.implementation.ApartamentReservedController;
import com.lyndexter.airbnb.controller.implementation.LessorController;
import com.lyndexter.airbnb.controller.implementation.PhotoController;
import com.lyndexter.airbnb.controller.implementation.ResponseController;
import com.lyndexter.airbnb.model.Apartament;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ApartamentDto extends ResourceSupport {

  private final Apartament apartament;

  public ApartamentDto(Apartament apartament, Link selfLink) {
    this.apartament = apartament;

    add(selfLink);
    add(
        linkTo(methodOn(LessorController.class).getLessor(apartament.getLessor().getId()))
            .withRel("lessor"));
    add(
        linkTo(methodOn(PhotoController.class).getPhotosByApartament(apartament.getId()))
            .withRel("apartamentPhotos"));
    add(
        linkTo(methodOn(ResponseController.class).getResponsesByApartament(apartament.getId()))
            .withRel("apartamentResponses"));

    add(
        linkTo(
                methodOn(ApartamentReservedController.class)
                    .getApartamentsReservedByApartament(apartament.getId()))
            .withRel("apartamentsReserved"));
  }

  public Integer getApartamentId() {
    return apartament.getId();
  }

  public Double getArea() {
    return apartament.getArea();
  }

  public String getAdress() {
    return apartament.getAdress();
  }

  public Double getCeilingHigh() {
    return apartament.getCeilingHigh();
  }

  public Integer getApartamentRoomNumber() {
    return apartament.getRoomNumber();
  }

  public Integer getRoomNumber() {
    return apartament.getRecomendedPeopleCount();
  }

  public BigDecimal getPrice() {
    return apartament.getPrice();
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

    ApartamentDto that = (ApartamentDto) o;

    return apartament != null ? apartament.equals(that.apartament) : that.apartament == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (apartament != null ? apartament.hashCode() : 0);
    return result;
  }
}
