package com.lyndexter.airbnb.controller.implementation;

import com.lyndexter.airbnb.controller.ControllerWithDto;
import com.lyndexter.airbnb.dto.PaymentTransactionDto;
import com.lyndexter.airbnb.model.PaymentTransaction;
import com.lyndexter.airbnb.service.implementation.PaymentTransactionService;
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
public class PaymentTransactionController
    implements ControllerWithDto<PaymentTransactionDto, PaymentTransaction> {
  private final PaymentTransactionService service;

  public PaymentTransactionController(PaymentTransactionService paymentTransactionService) {
    this.service = paymentTransactionService;
  }

  @GetMapping(value = "/lyndexter/paymentTransaction")
  public ResponseEntity<List<PaymentTransactionDto>> getPaymentTransactions() {
    List<PaymentTransaction> paymentTransactions = service.getEntities();
    List<PaymentTransactionDto> paymentTransactionDtos = createDtos(paymentTransactions);

    return new ResponseEntity<>(paymentTransactionDtos, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/paymentTransaction/{paymentTransactionId}")
  public ResponseEntity<PaymentTransactionDto> getPaymentTransaction(
      @PathVariable Integer paymentTransactionId) {
    PaymentTransaction paymentTransaction = service.getEntity(paymentTransactionId);
    PaymentTransactionDto paymentTransactionDto = createDto(paymentTransaction);

    return new ResponseEntity<>(paymentTransactionDto, HttpStatus.OK);
  }

  @PostMapping(value = "/lyndexter/paymentTransaction")
  public ResponseEntity<PaymentTransactionDto> setPaymentTransaction(
      @RequestBody PaymentTransaction paymentTransaction) {
    PaymentTransaction newPaymentTransaction = service.createEntity(paymentTransaction);
    PaymentTransactionDto paymentTransactionDto = createDto(newPaymentTransaction);

    return new ResponseEntity<>(paymentTransactionDto, HttpStatus.OK);
  }

  @PutMapping(value = "/lyndexter/paymentTransaction/{paymentTransactionId}")
  public ResponseEntity<PaymentTransactionDto> updatePaymentTransaction(
      @RequestBody PaymentTransaction paymentTransaction,
      @PathVariable Integer paymentTransactionId) {
    PaymentTransaction newPaymentTransaction =
        service.updateEntity(paymentTransaction, paymentTransactionId);
    PaymentTransactionDto paymentTransactionDto = createDto(newPaymentTransaction);

    return new ResponseEntity<>(paymentTransactionDto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/lyndexter/paymentTransaction/{paymentTransactionId}")
  public ResponseEntity<PaymentTransaction> deletePaymentTransaction(
      @PathVariable Integer paymentTransactionId) {
    service.deleteEntity(paymentTransactionId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/paymentTransaction/lessor/{lessorId}")
  public ResponseEntity<List<PaymentTransactionDto>> getPaymentTransactionByLessor(
      @PathVariable Integer lessorId) {
    Set<PaymentTransaction> paymentTransactions =
        service.getPaymentTransactionsByLessorId(lessorId);
    List<PaymentTransactionDto> paymentTransactionsDto = createDtos(paymentTransactions);

    return new ResponseEntity<>(paymentTransactionsDto, HttpStatus.OK);
  }

  @GetMapping(value = "/lyndexter/paymentTransaction/renter/{renterId}")
  public ResponseEntity<List<PaymentTransactionDto>> getPaymentTransactionByRenter(
      @PathVariable Integer renterId) {
    Set<PaymentTransaction> paymentTransactions =
        service.getPaymentTransactionsByRenterId(renterId);
    List<PaymentTransactionDto> paymentTransactionsDto = createDtos(paymentTransactions);

    return new ResponseEntity<>(paymentTransactionsDto, HttpStatus.OK);
  }

  @Override
  public List<PaymentTransactionDto> createDtos(Iterable<PaymentTransaction> paymentTransactions) {
    List<PaymentTransactionDto> paymentTransactionsDto = new ArrayList<>();
    for (PaymentTransaction paymentTransaction : paymentTransactions) {
      PaymentTransactionDto paymentTransactionDto = new PaymentTransactionDto(paymentTransaction);
      paymentTransactionsDto.add(paymentTransactionDto);
    }
    return paymentTransactionsDto;
  }

  @Override
  public PaymentTransactionDto createDto(PaymentTransaction paymentTransaction) {
    return new PaymentTransactionDto(paymentTransaction);
  }
}
