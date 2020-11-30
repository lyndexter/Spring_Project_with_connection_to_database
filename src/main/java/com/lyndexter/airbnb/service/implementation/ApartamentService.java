package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.exeption.NoSuchApartamentException;
import com.lyndexter.airbnb.exeption.NoSuchLessorException;
import com.lyndexter.airbnb.model.Apartament;
import com.lyndexter.airbnb.model.Lessor;
import com.lyndexter.airbnb.model.Response;
import com.lyndexter.airbnb.repository.ApartamentRepository;
import com.lyndexter.airbnb.repository.LessorRepository;
import com.lyndexter.airbnb.repository.ResponseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ApartamentService extends CommonServiceImplementation<Apartament, Integer> {

  private final JpaRepository<Apartament, Integer> repository;
  private final JpaRepository<Lessor, Integer> lessorRepository;
  private final JpaRepository<Response, Integer> responseRepository;

  public ApartamentService(
      ApartamentRepository repository,
      LessorRepository lessorRepository,
      ResponseRepository responseRepository) {
    this.repository = repository;
    this.lessorRepository = lessorRepository;
    this.responseRepository = responseRepository;
  }

  @Override
  protected JpaRepository<Apartament, Integer> getRepository() {
    return repository;
  }

  @Override
  protected void throwExeption() {
    throw new NoSuchApartamentException();
  }

  @Override
  protected Apartament mergeEntities(Apartament newEntity, Apartament entity) {

    newEntity.setArea(entity.getArea() == null ? entity.getArea() : newEntity.getArea());
    newEntity.setAdress(entity.getAdress() == null ? entity.getAdress() : newEntity.getAdress());
    newEntity.setCeilingHigh(
        entity.getCeilingHigh() == null ? entity.getCeilingHigh() : newEntity.getCeilingHigh());
    newEntity.setRoomNumber(
        entity.getRoomNumber() == null ? entity.getRoomNumber() : newEntity.getRoomNumber());
    newEntity.setRecomendedPeopleCount(
        entity.getRecomendedPeopleCount() == null
            ? entity.getRecomendedPeopleCount()
            : newEntity.getRecomendedPeopleCount());
    newEntity.setPrice(entity.getPrice() == null ? entity.getPrice() : newEntity.getPrice());
    newEntity.setLessor(entity.getLessor() == null ? entity.getLessor() : newEntity.getLessor());

    return newEntity;
  }

  @Override
  protected void checkIfEmpty(Apartament entity) {
    if (repository.existsById(entity.getId())) {
      throw new AlreadyExistsApartamentException();
    }
  }

  public Set<Apartament> getApartamentsByLessorId(Integer lessorId) {
    if (lessorRepository.existsById(lessorId)) {
      Lessor lessor = lessorRepository.findById(lessorId).get();
      return lessor.getApartaments();
    }
    throw new NoSuchLessorException();
  }

  public Set<Apartament> getApartamentsByResponseId(Integer responseId) {
    if (responseRepository.existsById(responseId)) {
      Response response = responseRepository.findById(responseId).get();
      return response.getApartaments();
    }
    throw new NoSuchLessorException();
  }
}
