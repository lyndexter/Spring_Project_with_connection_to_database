package com.lyndexter.airbnb.dto;

import com.lyndexter.airbnb.controller.implementation.ApartamentController;
import com.lyndexter.airbnb.controller.implementation.PhotoController;
import com.lyndexter.airbnb.controller.implementation.ResponseController;
import com.lyndexter.airbnb.model.Response;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ResponseDto extends ResourceSupport {
  private final Response response;

  public ResponseDto(Response response) {
    this.response = response;
    add(linkTo(methodOn(ResponseController.class).getResponse(response.getId())).withSelfRel());
    add(
        linkTo(methodOn(PhotoController.class).getPhotosByResponse(response.getId()))
            .withRel("responsePhotos"));
    add(
        linkTo(methodOn(ApartamentController.class).getApartamentsByResponse(response.getId()))
            .withRel("responseApartament"));
  }

  public Integer getLessorId() {
    return response.getId();
  }

  public Integer getRate() {
    return response.getRate();
  }

  public String getComent() {
    return response.getComent();
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

    ResponseDto that = (ResponseDto) o;

    return response != null ? response.equals(that.response) : that.response == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (response != null ? response.hashCode() : 0);
    return result;
  }
}
