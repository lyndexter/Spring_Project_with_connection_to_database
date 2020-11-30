package com.lyndexter.airbnb.controller.implementation;

import com.lyndexter.airbnb.controller.ControllerWithDto;
import com.lyndexter.airbnb.dto.PhotoDto;
import com.lyndexter.airbnb.model.Photo;
import com.lyndexter.airbnb.service.implementation.PhotoService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PhotoController implements ControllerWithDto<PhotoDto, Photo> {
  private final PhotoService photoService;

  public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
  }

  @GetMapping(value = "/lyndexter/photo")
  public ResponseEntity<List<PhotoDto>> getPhotos() {
    List<Photo> photos = photoService.getEntities();
    List<PhotoDto> photosDto = createDtos(photos);

    return new ResponseEntity<>(photosDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/photo/{photoId}")
  public ResponseEntity<PhotoDto> getPhoto(@PathVariable Integer photoId) {
    Photo photo = photoService.getEntity(photoId);
    PhotoDto photoDto = createDto(photo);

    return new ResponseEntity<>(photoDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/photo")
  public ResponseEntity<PhotoDto> setPhoto(@RequestBody Photo photo) {
    Photo newPhoto = photoService.createEntity(photo);
    PhotoDto photoDto = createDto(newPhoto);

    return new ResponseEntity<>(photoDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/photo/{photoId}")
  public ResponseEntity<PhotoDto> updatePhoto(
      @RequestBody Photo photo, @PathVariable Integer photoId) {
    Photo newPhoto = photoService.updateEntity(photo, photoId);
    PhotoDto photoDto = createDto(newPhoto);

    return new ResponseEntity<>(photoDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/photo/{photoId}")
  public ResponseEntity<Photo> deletePhoto(@PathVariable Integer photoId) {
    photoService.deleteEntity(photoId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/photo/apartament/{apartamentId}")
  public ResponseEntity<List<PhotoDto>> getPhotosByApartament(@PathVariable Integer apartamentId) {
    Set<Photo> photos = photoService.getPhotosByApartamentId(apartamentId);
    List<PhotoDto> photosDto = createDtos(photos);

    return new ResponseEntity<>(photosDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/photo/response/{responseId}")
  public ResponseEntity<List<PhotoDto>> getPhotosByResponse(@PathVariable Integer responseId) {
    Set<Photo> photos = photoService.getPhotosByResponseId(responseId);
    List<PhotoDto> photosDto = createDtos(photos);

    return new ResponseEntity<>(photosDto, HttpStatus.OK);
  }

  @Override
  public List<PhotoDto> createDtos(Iterable<Photo> photos) {
    Link link = linkTo(methodOn(PhotoController.class).getPhotos()).withSelfRel();
    List<PhotoDto> photosDto = new ArrayList<>();
    for (Photo photo : photos) {
      Link selfLink = new Link(link.getHref() + "/" + photo.getId()).withSelfRel();
      PhotoDto photoDto = new PhotoDto(photo, selfLink);
      photosDto.add(photoDto);
    }
    return photosDto;
  }

  @Override
  public PhotoDto createDto(Photo photo) {
    Link link = linkTo(methodOn(PhotoController.class).getPhoto(photo.getId())).withSelfRel();
    return new PhotoDto(photo, link);
  }
}
