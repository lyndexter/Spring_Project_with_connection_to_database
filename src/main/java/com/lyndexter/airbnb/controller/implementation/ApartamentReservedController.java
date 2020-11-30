package com.lyndexter.airbnb.controller.implementation;

import com.lyndexter.airbnb.controller.ControllerWithDto;
import com.lyndexter.airbnb.dto.ApartamentReservedDto;
import com.lyndexter.airbnb.model.ApartamentReserved;
import com.lyndexter.airbnb.service.implementation.ApartamentReservedService;
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

@RestController
public class ApartamentReservedController
    implements ControllerWithDto<ApartamentReservedDto, ApartamentReserved> {
  private final ApartamentReservedService service;

  public ApartamentReservedController(ApartamentReservedService apartamentReservedService) {
    this.service = apartamentReservedService;
  }

  @GetMapping(value = "/lyndexter/apartamentReserved")
  public ResponseEntity<List<ApartamentReservedDto>> getApartamentsReserved() {
    List<ApartamentReserved> apartamentsReserved = service.getEntities();
    List<ApartamentReservedDto> apartamentsReservedDto = createDtos(apartamentsReserved);

    return new ResponseEntity<>(apartamentsReservedDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/apartamentReserved/{apartamentReservedId}")
  public ResponseEntity<ApartamentReservedDto> getApartamentReserved(
      @PathVariable Integer apartamentReservedId) {
    ApartamentReserved apartamentReserved = service.getEntity(apartamentReservedId);
    ApartamentReservedDto apartamentReservedDto = createDto(apartamentReserved);

    return new ResponseEntity<>(apartamentReservedDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/apartamentReserved")
  public ResponseEntity<ApartamentReservedDto> setApartamentReserved(
      @RequestBody ApartamentReserved apartamentReserved) {
    ApartamentReserved newApartamentReserved = service.createEntity(apartamentReserved);
    ApartamentReservedDto apartamentReservedDto = createDto(newApartamentReserved);

    return new ResponseEntity<>(apartamentReservedDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/apartamentReserved/{apartamentReservedId}")
  public ResponseEntity<ApartamentReservedDto> updateApartamentReserved(
      @RequestBody ApartamentReserved apartamentReserved,
      @PathVariable Integer apartamentReservedId) {

    ApartamentReserved newApartamentReserved =
        service.updateEntity(apartamentReserved, apartamentReservedId);
    ApartamentReservedDto apartamentReservedDto = createDto(newApartamentReserved);

    return new ResponseEntity<>(apartamentReservedDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/apartamentReserved/{apartamentReservedId}")
  public ResponseEntity<ApartamentReserved> deleteApartamentReserved(
      @PathVariable Integer apartamentReservedId) {
    service.deleteEntity(apartamentReservedId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/apartamentReserved/paymentTransaction/{paymentTransactionId}")
  public ResponseEntity<List<ApartamentReservedDto>> getApartamentsReservedByPaymentTransaction(
      @PathVariable Integer paymentTransactionId) {
    Set<ApartamentReserved> apartamentsReserved =
        service.getApartamentsReservedByPaymentTransactionId(paymentTransactionId);
    List<ApartamentReservedDto> apartamentsReservedDto = createDtos(apartamentsReserved);

    return new ResponseEntity<>(apartamentsReservedDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/apartamentReserved/apartament/{apartamentId}")
  public ResponseEntity<List<ApartamentReservedDto>> getApartamentsReservedByApartament(
      @PathVariable Integer apartamentId) {
    Set<ApartamentReserved> apartamentsReserved =
        service.getApartamentsReservedByApartamentId(apartamentId);
    List<ApartamentReservedDto> apartamentsReservedDto = createDtos(apartamentsReserved);

    return new ResponseEntity<>(apartamentsReservedDto, HttpStatus.OK);
  }

  @Override
  public List<ApartamentReservedDto> createDtos(Iterable<ApartamentReserved> apartamentReserveds) {
    List<ApartamentReservedDto> apartamentsReservedDto = new ArrayList<>();
    for (ApartamentReserved apartamentReserved : apartamentReserveds) {
      ApartamentReservedDto apartamentReservedDto = new ApartamentReservedDto(apartamentReserved);
      apartamentsReservedDto.add(apartamentReservedDto);
    }
    return apartamentsReservedDto;
  }

  @Override
  public ApartamentReservedDto createDto(ApartamentReserved apartamentReserved) {
    return new ApartamentReservedDto(apartamentReserved);
  }
}
