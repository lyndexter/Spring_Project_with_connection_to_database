package com.lyndexter.airbnb.controller.implementation;

import com.lyndexter.airbnb.controller.ControllerWithDto;
import com.lyndexter.airbnb.dto.RenterDto;
import com.lyndexter.airbnb.model.Renter;
import com.lyndexter.airbnb.service.implementation.RenterService;
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

@RestController
public class RenterController implements ControllerWithDto<RenterDto, Renter> {
  private final RenterService service;

  public RenterController(RenterService renterService) {
    this.service = renterService;
  }

  @GetMapping(value = "/lyndexter/renter")
  public ResponseEntity<List<RenterDto>> getRenters() {
    List<Renter> renters = service.getEntities();
    List<RenterDto> rentersDto = createDtos(renters);

    return new ResponseEntity<>(rentersDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/renter/{renterId}")
  public ResponseEntity<RenterDto> getRenter(@PathVariable Integer renterId) {
    Renter renter = service.getEntity(renterId);
    RenterDto renterDto = createDto(renter);

    return new ResponseEntity<>(renterDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/renter")
  public ResponseEntity<RenterDto> setRenter(@RequestBody Renter renter) {
    Renter newRenter = service.createEntity(renter);
    RenterDto renterDto = createDto(newRenter);

    return new ResponseEntity<>(renterDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/renter/{renterId}")
  public ResponseEntity<RenterDto> updateRenter(
      @RequestBody Renter renter, @PathVariable Integer renterId) {
    Renter newRenter = service.updateEntity(renter, renterId);
    RenterDto renterDto = createDto(newRenter);

    return new ResponseEntity<>(renterDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/renter/{renterId}")
  public ResponseEntity<Renter> deleteRenter(@PathVariable Integer renterId) {
    service.deleteEntity(renterId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<RenterDto> createDtos(Iterable<Renter> renters) {
    List<RenterDto> rentersDto = new ArrayList<>();
    for (Renter renter : renters) {
      RenterDto renterDto = new RenterDto(renter);
      rentersDto.add(renterDto);
    }
    return rentersDto;
  }

  @Override
  public RenterDto createDto(Renter renter) {
    return new RenterDto(renter);
  }
}
