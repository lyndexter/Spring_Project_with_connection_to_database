package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.exeption.NoSuchLessorException;
import com.lyndexter.airbnb.model.Lessor;
import com.lyndexter.airbnb.repository.LessorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LessorService extends CommonServiceImplementation<Lessor, Integer> {

  private final JpaRepository<Lessor, Integer> repository;

  public LessorService(LessorRepository repository) {
    this.repository = repository;
  }

  @Override
  protected JpaRepository<Lessor, Integer> getRepository() {
    return repository;
  }

  @Override
  protected void throwExeption() {
    throw new NoSuchLessorException();
  }

  @Override
  protected Lessor mergeEntities(Lessor newEntity, Lessor entity) {

    newEntity.setName(entity.getName() == null ? entity.getName() : newEntity.getName());
    newEntity.setSurname(
        entity.getSurname() != null ? entity.getSurname() : newEntity.getSurname());
    newEntity.setLastName(
        entity.getLastName() != null ? entity.getLastName() : newEntity.getLastName());
    newEntity.setPhoneNumber(
        entity.getPhoneNumber() != null ? entity.getPhoneNumber() : newEntity.getPhoneNumber());
    newEntity.setCardNumber(
        entity.getCardNumber() != null ? entity.getCardNumber() : newEntity.getCardNumber());
    newEntity.setContactInfo(
        entity.getContactInfo() != null ? entity.getContactInfo() : newEntity.getContactInfo());
    newEntity.setPhoto(entity.getPhoto() != null ? entity.getPhoto() : newEntity.getPhoto());
    newEntity.setApartaments(
        entity.getApartaments() != null ? entity.getApartaments() : newEntity.getApartaments());

    return newEntity;
  }
}
