package com.lyndexter.airbnb.dto;

import com.lyndexter.airbnb.model.Photo;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class PhotoDto extends ResourceSupport {

  private final Photo photo;

  public PhotoDto(Photo photo, Link selfLink) {
    this.photo = photo;
    add(selfLink);
  }

  public Integer getPhotoId() {
    return photo.getId();
  }

  public byte[] getImage() {
    return photo.getImage();
  }

  public String getImageSize() {
    return photo.getImageSize();
  }

  public String getName() {
    return photo.getName();
  }

  public String getType() {
    return photo.getType();
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

    PhotoDto photoDto = (PhotoDto) o;

    return photo != null ? photo.equals(photoDto.photo) : photoDto.photo == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (photo != null ? photo.hashCode() : 0);
    return result;
  }
}
