package com.lyndexter.airbnb.controller.implementation;

import com.lyndexter.airbnb.controller.ControllerWithDto;
import com.lyndexter.airbnb.dto.ApartamentDto;
import com.lyndexter.airbnb.model.Apartament;
import com.lyndexter.airbnb.service.implementation.ApartamentService;
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
public class ApartamentController implements ControllerWithDto<ApartamentDto, Apartament> {
  private final ApartamentService service;

  public ApartamentController(ApartamentService apartamentService) {
    this.service = apartamentService;
  }

  @GetMapping(value = "/lyndexter/apartament")
  public ResponseEntity<List<ApartamentDto>> getApartaments() {
    List<Apartament> apartaments = service.getEntities();
    List<ApartamentDto> apartamentsDto = createDtos(apartaments);

    return new ResponseEntity<>(apartamentsDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/apartament/{apartamentId}")
  public ResponseEntity<ApartamentDto> getApartament(@PathVariable Integer apartamentId) {
    Apartament apartament = service.getEntity(apartamentId);
    ApartamentDto apartamentDto = createDto(apartament);

    return new ResponseEntity<>(apartamentDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/apartament")
  public ResponseEntity<ApartamentDto> setApartament(@RequestBody Apartament apartament) {
    Apartament newApartament = service.createEntity(apartament);
    ApartamentDto apartamentDto = createDto(newApartament);

    return new ResponseEntity<>(apartamentDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/apartament/{apartamentId}")
  public ResponseEntity<ApartamentDto> updateApartament(
      @RequestBody Apartament apartament, @PathVariable Integer apartamentId) {
    Apartament newApartament = service.updateEntity(apartament, apartamentId);
    ApartamentDto apartamentDto = createDto(newApartament);

    return new ResponseEntity<>(apartamentDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/apartament/{apartamentId}")
  public ResponseEntity<Apartament> deleteApartament(@PathVariable Integer apartamentId) {
    service.deleteEntity(apartamentId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/apartament/lessor/{lessorId}")
  public ResponseEntity<List<ApartamentDto>> getApartamentsByLessor(
      @PathVariable Integer lessorId) {
    Set<Apartament> apartaments = service.getApartamentsByLessorId(lessorId);
    List<ApartamentDto> apartamentsDto = createDtos(apartaments);

    return new ResponseEntity<>(apartamentsDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/apartament/{apartamentId}/response/{responseId}")
  public ResponseEntity<List<ApartamentDto>> getApartamentsByResponse(
      @PathVariable Integer responseId) {
    Set<Apartament> apartaments = service.getApartamentsByResponseId(responseId);
    List<ApartamentDto> apartamentsDto = createDtos(apartaments);

    return new ResponseEntity<>(apartamentsDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/apartament/{apartamentId}/response/{responseId}")
  public ResponseEntity<ApartamentDto> setResponseForApartament(
      @PathVariable Integer apartamentId, @PathVariable Integer responseId) {
    Apartament apartament = service.setApartamentToResponse(apartamentId, responseId);
    ApartamentDto apartamentsDto = createDto(apartament);

    return new ResponseEntity<>(apartamentsDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/apartament/{apartamentId}/response/{responseId}")
  public ResponseEntity<ApartamentDto> updateResponseForApartament(
      @PathVariable Integer apartamentId, @PathVariable Integer responseId) {
    Apartament apartament = service.updateApartamentToResponse(apartamentId, responseId);
    ApartamentDto apartamentsDto = createDto(apartament);

    return new ResponseEntity<>(apartamentsDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/apartament/{apartamentId}/response/{responseId}")
  public ResponseEntity<ApartamentDto> deleteResponseForApartament(
      @PathVariable Integer apartamentId, @PathVariable Integer responseId) {
    Apartament apartament = service.deleteApartamentToResponse(apartamentId, responseId);
    ApartamentDto apartamentsDto = createDto(apartament);

    return new ResponseEntity<>(apartamentsDto, HttpStatus.OK);
  }

  @Override
  public List<ApartamentDto> createDtos(Iterable<Apartament> apartaments) {
    Link link = linkTo(methodOn(ApartamentController.class).getApartaments()).withSelfRel();
    List<ApartamentDto> apartamentsDto = new ArrayList<>();
    for (Apartament apartament : apartaments) {
      Link selfLink = new Link(link.getHref() + "/" + apartament.getId());
      ApartamentDto apartamentDto = new ApartamentDto(apartament, selfLink);
      apartamentsDto.add(apartamentDto);
    }
    return apartamentsDto;
  }

  @Override
  public ApartamentDto createDto(Apartament apartament) {
    Link selfLink =
        linkTo(methodOn(ApartamentController.class).getApartament(apartament.getId()))
            .withSelfRel();
    return new ApartamentDto(apartament, selfLink);
  }
}
