package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.exeption.NoSuchRenterException;
import com.lyndexter.airbnb.model.Renter;
import com.lyndexter.airbnb.repository.RenterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RenterService extends CommonServiceImplementation<Renter, Integer> {

  private final JpaRepository<Renter, Integer> repository;

  public RenterService(RenterRepository repository) {
    this.repository = repository;
  }

  @Override
  protected JpaRepository<Renter, Integer> getRepository() {
    return repository;
  }

  @Override
  protected void throwExeption() {
    throw new NoSuchRenterException();
  }

  @Override
  protected Renter mergeEntities(Renter newEntity, Renter entity) {

    newEntity.setName(entity.getName() == null ? entity.getName() : newEntity.getName());
    newEntity.setSurname(
        entity.getSurname() == null ? entity.getSurname() : newEntity.getSurname());
    newEntity.setLastName(
        entity.getLastName() == null ? entity.getLastName() : newEntity.getLastName());
    newEntity.setPhoneNumber(
        entity.getPhoneNumber() == null ? entity.getPhoneNumber() : newEntity.getPhoneNumber());
    newEntity.setCardNumber(
        entity.getCardNumber() == null ? entity.getCardNumber() : newEntity.getCardNumber());
    newEntity.setPhoto(entity.getPhoto() == null ? entity.getPhoto() : newEntity.getPhoto());

    return newEntity;
  }
  
}
