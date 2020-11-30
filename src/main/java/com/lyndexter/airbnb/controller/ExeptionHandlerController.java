package com.lyndexter.airbnb.controller;

import com.lyndexter.airbnb.dto.MessageDto;
import com.lyndexter.airbnb.exeption.NoSuchApartamentException;
import com.lyndexter.airbnb.exeption.NoSuchApartamentReservedException;
import com.lyndexter.airbnb.exeption.NoSuchLessorException;
import com.lyndexter.airbnb.exeption.NoSuchPaymentTransactionException;
import com.lyndexter.airbnb.exeption.NoSuchPhotoException;
import com.lyndexter.airbnb.exeption.NoSuchRenterException;
import com.lyndexter.airbnb.exeption.NoSuchResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NoSuchPhotoException.class)
  ResponseEntity<MessageDto> handleNoSushCityException() {
    return new ResponseEntity<>(
        new MessageDto("Such Photo is not present in database"), HttpStatus.NOT_FOUND);
  }

  //  @ExceptionHandler(AlreadyExistsPhotoException.class)
  //  ResponseEntity<MessageDto> handleAlreadyExistsPhotoException() {
  //    return new ResponseEntity<>(
  //        new MessageDto("Such Photo is already exist in database"), HttpStatus.NOT_FOUND);
  //  }

  @ExceptionHandler(NoSuchApartamentException.class)
  ResponseEntity<MessageDto> handleNoSuchApartamentException() {
    return new ResponseEntity<>(
        new MessageDto("Such Apartament is not present in database"), HttpStatus.NOT_FOUND);
  }
  //
  //  @ExceptionHandler(AlreadyExistsApartamentException.class)
  //  ResponseEntity<MessageDto> handleAlreadyExistsApartamentException() {
  //    return new ResponseEntity<>(
  //        new MessageDto("Such Apartament already exist in database"), HttpStatus.NOT_FOUND);
  //  }

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
}
