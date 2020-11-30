package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.exeption.NoSuchApartamentException;
import com.lyndexter.airbnb.exeption.NoSuchResponseException;
import com.lyndexter.airbnb.model.Apartament;
import com.lyndexter.airbnb.model.Response;
import com.lyndexter.airbnb.repository.ApartamentRepository;
import com.lyndexter.airbnb.repository.ResponseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ResponseService extends CommonServiceImplementation<Response, Integer> {

  private final JpaRepository<Response, Integer> repository;
  private final JpaRepository<Apartament, Integer> apartamentRepository;

  public ResponseService(ResponseRepository repository, ApartamentRepository apartamentRepository) {
    this.repository = repository;
    this.apartamentRepository = apartamentRepository;
  }

  @Override
  protected JpaRepository<Response, Integer> getRepository() {
    return repository;
  }

  @Override
  protected void throwExeption() {
    throw new NoSuchResponseException();
  }

  @Override
  protected Response mergeEntities(Response newEntity, Response entity) {

    newEntity.setComent(entity.getComent() == null ? entity.getComent() : newEntity.getComent());
    newEntity.setComent(entity.getComent() == null ? entity.getComent() : newEntity.getComent());
    newEntity.setPhotos(entity.getPhotos() == null ? entity.getPhotos() : newEntity.getPhotos());

    return newEntity;
  }

  @Override
  protected void checkIfEmpty(Response entity) {
    if (repository.existsById(entity.getId())) {
      throw new AlreadyExistsRenterException();
    }
  }

  public Set<Response> getResponsesByApartamentId(Integer apartamentId) {
    if (apartamentRepository.existsById(apartamentId)) {
      Apartament apartament = apartamentRepository.findById(apartamentId).get();
      return apartament.getResponses();
    }
    throw new NoSuchApartamentException();
  }
}
