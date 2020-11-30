package com.lyndexter.airbnb.controller.implementation;

import com.lyndexter.airbnb.controller.ControllerWithDto;
import com.lyndexter.airbnb.dto.LessorDto;
import com.lyndexter.airbnb.model.Lessor;
import com.lyndexter.airbnb.service.implementation.LessorService;
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
public class LessorController implements ControllerWithDto<LessorDto, Lessor> {
  private final LessorService service;

  public LessorController(LessorService lessorService) {
    this.service = lessorService;
  }

  @GetMapping(value = "/lyndexter/lessor")
  public ResponseEntity<List<LessorDto>> getLessors() {
    List<Lessor> lessors = service.getEntities();
    List<LessorDto> lessorsDto = createDtos(lessors);
    return new ResponseEntity<>(lessorsDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/lessor/{lessorId}")
  public ResponseEntity<LessorDto> getLessor(@PathVariable Integer lessorId) {
    Lessor lessor = service.getEntity(lessorId);
    LessorDto lessorDto = createDto(lessor);

    return new ResponseEntity<>(lessorDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/lessor")
  public ResponseEntity<LessorDto> setLessor(@RequestBody Lessor lessor) {
    Lessor newLessor = service.createEntity(lessor);
    LessorDto lessorDto = createDto(newLessor);

    return new ResponseEntity<>(lessorDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/lessor/{lessorId}")
  public ResponseEntity<LessorDto> updateLessor(
      @RequestBody Lessor lessor, @PathVariable Integer lessorId) {
    Lessor newLessor = service.updateEntity(lessor, lessorId);
    LessorDto lessorDto = createDto(newLessor);

    return new ResponseEntity<>(lessorDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/lessor/{lessorId}")
  public ResponseEntity<Lessor> deleteLessor(@PathVariable Integer lessorId) {
    service.deleteEntity(lessorId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<LessorDto> createDtos(Iterable<Lessor> lessors) {
    List<LessorDto> lessorsDto = new ArrayList<>();
    for (Lessor lessor : lessors) {
      LessorDto lessorDto = new LessorDto(lessor);
      lessorsDto.add(lessorDto);
    }
    return lessorsDto;
  }

  @Override
  public LessorDto createDto(Lessor lessor) {
    return new LessorDto(lessor);
  }
}
