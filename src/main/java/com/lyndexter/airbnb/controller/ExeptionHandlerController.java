package com.lyndexter.airbnb.controller;

import com.lyndexter.airbnb.dto.MessageDto;
import com.lyndexter.airbnb.exeption.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NoSuchPhotoException.class)
  ResponseEntity<MessageDto> handleNoSuсhЗрщещException() {
    return new ResponseEntity<>(
        new MessageDto("Such Photo is not present in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchApartamentException.class)
  ResponseEntity<MessageDto> handleNoSuchApartamentException() {
    return new ResponseEntity<>(
        new MessageDto("Such Apartament is not present in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchRenterException.class)
  ResponseEntity<MessageDto> handleNoSuchRenterException() {
    return new ResponseEntity<>(
        new MessageDto("Such Renter is not present in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchLessorException.class)
  ResponseEntity<MessageDto> handleNoSuchLessorException() {
    return new ResponseEntity<>(
        new MessageDto("Such Lessor is not present in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchResponseException.class)
  ResponseEntity<MessageDto> handleNoSuchResponseException() {
    return new ResponseEntity<>(
        new MessageDto("Such Response is not present in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchApartamentReservedException.class)
  ResponseEntity<MessageDto> handleNoSuchApartamentReservedException() {
    return new ResponseEntity<>(
        new MessageDto("Such Apartament reservation is not present in database"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchPaymentTransactionException.class)
  ResponseEntity<MessageDto> handleNoSuchPaymentTransactionException() {
    return new ResponseEntity<>(
        new MessageDto("Such Payment transaction is not present in database"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AlreadyExistRelationApartamentResponseException.class)
  ResponseEntity<MessageDto> handleAlreadyExistRelationApartamentResponseException() {
    return new ResponseEntity<>(
        new MessageDto("Such Relation Apartament Response is already present in database"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchRelationApartamentResponseException.class)
  ResponseEntity<MessageDto> handleNoSuchRelationApartamentResponseException() {
    return new ResponseEntity<>(
        new MessageDto("Such Relation Apartament Response is not present in database"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AlreadyExistRelationResponsePhotoException.class)
  ResponseEntity<MessageDto> handleAlreadyExistRelationResponsePhotoException() {
    return new ResponseEntity<>(
        new MessageDto("Such Relation Photo Response is already present in database"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchRelationResponsePhotoException.class)
  ResponseEntity<MessageDto> handleNoSuchRelationResponsePhotoException() {
    return new ResponseEntity<>(
        new MessageDto("Such Relation Photo Response is not present in database"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AlreadyExistRelationApartamentPhotoException.class)
  ResponseEntity<MessageDto> handleAlreadyExistRelationApartamentPhotoException() {
    return new ResponseEntity<>(
        new MessageDto("Such Relation Apartament Photo is already present in database"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchRelationApartamentPhotoException.class)
  ResponseEntity<MessageDto> handleNoSuchRelationApartamentPhotoException() {
    return new ResponseEntity<>(
        new MessageDto("Such Relation Apartament Photo is not present in database"),
        HttpStatus.NOT_FOUND);
  }
}
