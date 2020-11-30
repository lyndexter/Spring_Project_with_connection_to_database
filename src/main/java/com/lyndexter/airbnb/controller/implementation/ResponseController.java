package com.lyndexter.airbnb.controller.implementation;

import com.lyndexter.airbnb.controller.ControllerWithDto;
import com.lyndexter.airbnb.dto.ResponseDto;
import com.lyndexter.airbnb.model.Response;
import com.lyndexter.airbnb.service.implementation.ResponseService;
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
public class ResponseController implements ControllerWithDto<ResponseDto, Response> {
  private final ResponseService service;

  public ResponseController(ResponseService responseService) {
    this.service = responseService;
  }

  @GetMapping(value = "/lyndexter/response")
  public ResponseEntity<List<ResponseDto>> getResponses() {
    List<Response> responses = service.getEntities();
    List<ResponseDto> responsesDto = createDtos(responses);

    return new ResponseEntity<>(responsesDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/response/{responseId}")
  public ResponseEntity<ResponseDto> getResponse(@PathVariable Integer responseId) {
    Response response = service.getEntity(responseId);
    ResponseDto responseDto = createDto(response);

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/response")
  public ResponseEntity<ResponseDto> setResponse(@RequestBody Response response) {
    Response newResponse = service.createEntity(response);
    ResponseDto responseDto = createDto(newResponse);

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/response/{responseId}")
  public ResponseEntity<ResponseDto> updateResponse(
      @RequestBody Response response, @PathVariable Integer responseId) {
    Response newResponse = service.updateEntity(response, responseId);
    ResponseDto responseDto = createDto(newResponse);

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/response/{responseId}")
  public ResponseEntity<Response> deleteResponse(@PathVariable Integer responseId) {
    service.deleteEntity(responseId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/response/apartament/{apartamentId}")
  public ResponseEntity<List<ResponseDto>> getResponsesByApartament(
      @PathVariable Integer apartamentId) {
    Set<Response> responses = service.getResponsesByApartamentId(apartamentId);
    List<ResponseDto> responsesDto = createDtos(responses);

    return new ResponseEntity<>(responsesDto, HttpStatus.OK);
  }

  @Override
  public List<ResponseDto> createDtos(Iterable<Response> responses) {
    List<ResponseDto> responsesDto = new ArrayList<>();
    for (Response response : responses) {
      ResponseDto responseDto = new ResponseDto(response);
      responsesDto.add(responseDto);
    }
    return responsesDto;
  }

  @Override
  public ResponseDto createDto(Response response) {
    return new ResponseDto(response);
  }
}
