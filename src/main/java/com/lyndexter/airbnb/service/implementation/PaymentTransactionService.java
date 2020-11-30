package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.exeption.NoSuchLessorException;
import com.lyndexter.airbnb.exeption.NoSuchPaymentTransactionException;
import com.lyndexter.airbnb.exeption.NoSuchRenterException;
import com.lyndexter.airbnb.model.Lessor;
import com.lyndexter.airbnb.model.PaymentTransaction;
import com.lyndexter.airbnb.model.Renter;
import com.lyndexter.airbnb.repository.LessorRepository;
import com.lyndexter.airbnb.repository.PaymentTransactionRepository;
import com.lyndexter.airbnb.repository.RenterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class PaymentTransactionService
    extends CommonServiceImplementation<PaymentTransaction, Integer> {

  private final JpaRepository<PaymentTransaction, Integer> repository;
  private final JpaRepository<Lessor, Integer> lessorRepository;
  private final JpaRepository<Renter, Integer> renterRepository;

  public PaymentTransactionService(
      PaymentTransactionRepository repository,
      LessorRepository lessorRepository,
      RenterRepository renterRepository) {
    this.repository = repository;
    this.lessorRepository = lessorRepository;
    this.renterRepository = renterRepository;
  }

  @Override
  protected JpaRepository<PaymentTransaction, Integer> getRepository() {
    return repository;
  }

  @Override
  protected void throwExeption() {
    throw new NoSuchPaymentTransactionException();
  }

  @Override
  protected PaymentTransaction mergeEntities(
      PaymentTransaction newEntity, PaymentTransaction entity) {

    newEntity.setRenter(entity.getRenter() == null ? entity.getRenter() : newEntity.getRenter());
    newEntity.setLessor(entity.getLessor() == null ? entity.getLessor() : newEntity.getLessor());
    newEntity.setRenterPayment(
        entity.getRenterPayment() == null
            ? entity.getRenterPayment()
            : newEntity.getRenterPayment());
    newEntity.setLessorRecieveMoney(
        entity.getLessorRecieveMoney() == null
            ? entity.getLessorRecieveMoney()
            : newEntity.getLessorRecieveMoney());

    return newEntity;
  }

  @Override
  protected void checkIfEmpty(PaymentTransaction entity) {
    if (repository.existsById(entity.getId())) {
      throw new AlreadyExistsPaymentTransaction();
    }
  }

  public Set<PaymentTransaction> getPaymentTransactionsByLessorId(Integer lessorId) {
    if (lessorRepository.existsById(lessorId)) {
      Lessor lessor = lessorRepository.findById(lessorId).get();
      return lessor.getPaymentTransactions();
    }
    throw new NoSuchLessorException();
  }

  public Set<PaymentTransaction> getPaymentTransactionsByRenterId(Integer renterId) {
    if (renterRepository.existsById(renterId)) {
      Renter renter = renterRepository.findById(renterId).get();
      return renter.getPaymentTransactions();
    }
    throw new NoSuchRenterException();
  }
}
