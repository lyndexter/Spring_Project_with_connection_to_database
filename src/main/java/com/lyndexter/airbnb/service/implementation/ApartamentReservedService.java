package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.exeption.NoSuchApartamentException;
import com.lyndexter.airbnb.exeption.NoSuchApartamentReservedException;
import com.lyndexter.airbnb.exeption.NoSuchPaymentTransactionException;
import com.lyndexter.airbnb.model.Apartament;
import com.lyndexter.airbnb.model.ApartamentReserved;
import com.lyndexter.airbnb.model.PaymentTransaction;
import com.lyndexter.airbnb.repository.ApartamentRepository;
import com.lyndexter.airbnb.repository.ApartamentReservedRepository;
import com.lyndexter.airbnb.repository.PaymentTransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ApartamentReservedService
    extends CommonServiceImplementation<ApartamentReserved, Integer> {

  private final JpaRepository<ApartamentReserved, Integer> repository;
  private final JpaRepository<PaymentTransaction, Integer> paymentTransactionRepository;
  private final JpaRepository<Apartament, Integer> apartamentRepository;

  public ApartamentReservedService(
      ApartamentReservedRepository repository,
      PaymentTransactionRepository paymentTransactionRepository,
      ApartamentRepository apartamentRepository) {
    this.repository = repository;
    this.paymentTransactionRepository = paymentTransactionRepository;
    this.apartamentRepository = apartamentRepository;
  }

  @Override
  protected JpaRepository<ApartamentReserved, Integer> getRepository() {
    return repository;
  }

  @Override
  protected void throwExeption() {
    throw new NoSuchApartamentReservedException();
  }

  @Override
  protected ApartamentReserved mergeEntities(
      ApartamentReserved newEntity, ApartamentReserved entity) {

    newEntity.setApartament(
        entity.getApartament() != null ? entity.getApartament() : newEntity.getApartament());
    newEntity.setReserved(
        entity.getReserved() != null ? entity.getReserved() : newEntity.getReserved());
    newEntity.setWish(entity.getWish() != null ? entity.getWish() : newEntity.getWish());
    newEntity.setPaymentTransaction(
        entity.getPaymentTransaction() != null
            ? entity.getPaymentTransaction()
            : newEntity.getPaymentTransaction());

    return newEntity;
  }

  public Set<ApartamentReserved> getApartamentsReservedByPaymentTransactionId(
      Integer paymentTransactionId) {
    if (paymentTransactionRepository.existsById(paymentTransactionId)) {
      PaymentTransaction paymentTransaction =
          paymentTransactionRepository.findById(paymentTransactionId).get();
      return paymentTransaction.getApartamentReserveds();
    }
    throw new NoSuchPaymentTransactionException();
  }

  public Set<ApartamentReserved> getApartamentsReservedByApartamentId(Integer apartamentId) {
    if (apartamentRepository.existsById(apartamentId)) {
      Apartament apartament = apartamentRepository.findById(apartamentId).get();
      return apartament.getApartamentReserveds();
    }
    throw new NoSuchApartamentException();
  }
}
