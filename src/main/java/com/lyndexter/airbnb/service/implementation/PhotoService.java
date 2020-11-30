package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.exeption.AlreadyExistRelationApartamentPhotoException;
import com.lyndexter.airbnb.exeption.AlreadyExistRelationResponsePhotoException;
import com.lyndexter.airbnb.exeption.NoSuchApartamentException;
import com.lyndexter.airbnb.exeption.NoSuchPhotoException;
import com.lyndexter.airbnb.exeption.NoSuchRelationApartamentPhotoException;
import com.lyndexter.airbnb.exeption.NoSuchRelationResponsePhotoException;
import com.lyndexter.airbnb.exeption.NoSuchResponseException;
import com.lyndexter.airbnb.model.Apartament;
import com.lyndexter.airbnb.model.Photo;
import com.lyndexter.airbnb.model.Response;
import com.lyndexter.airbnb.repository.ApartamentRepository;
import com.lyndexter.airbnb.repository.PhotoRepository;
import com.lyndexter.airbnb.repository.ResponseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Set;

@Service
public class PhotoService extends CommonServiceImplementation<Photo, Integer> {

  private final JpaRepository<Photo, Integer> repository;
  private final JpaRepository<Apartament, Integer> apartamentRepository;
  private final JpaRepository<Response, Integer> responseRepository;

  public PhotoService(
      PhotoRepository repository,
      ApartamentRepository apartamentRepository,
      ResponseRepository responseRepository) {
    this.repository = repository;
    this.apartamentRepository = apartamentRepository;
    this.responseRepository = responseRepository;
  }

  @Override
  protected JpaRepository<Photo, Integer> getRepository() {
    return repository;
  }

  @Override
  protected void throwExeption() {
    throw new NoSuchPhotoException();
  }

  @Override
  protected Photo mergeEntities(Photo newEntity, Photo entity) {
    byte[] sample = new byte[0];
    newEntity.setImage(entity.getImage() != sample ? entity.getImage() : newEntity.getImage());
    newEntity.setImageSize(
        entity.getImageSize() != null ? entity.getImageSize() : newEntity.getImageSize());
    newEntity.setName(entity.getName() != null ? entity.getName() : newEntity.getName());
    newEntity.setType(entity.getType() != null ? entity.getType() : newEntity.getType());

    return newEntity;
  }

  public Set<Photo> getPhotosByApartamentId(Integer apartamentId) {
    if (apartamentRepository.existsById(apartamentId)) {
      Apartament apartament = apartamentRepository.findById(apartamentId).get();
      return apartament.getPhotos();
    }
    throw new NoSuchApartamentException();
  }

  @Transactional
  public Photo setPhotoForApartament(Integer apartamentId, Integer photoId) {
    if (!apartamentRepository.existsById(apartamentId)) {
      throw new NoSuchApartamentException();
    }
    if (!repository.existsById(photoId)) {
      throw new NoSuchPhotoException();
    }
    Apartament apartament = apartamentRepository.findById(apartamentId).get();
    Photo photo = repository.findById(photoId).get();

    if (!photo.getApartaments().isEmpty()) {
      throw new AlreadyExistRelationApartamentPhotoException();
    }

    apartament.getPhotos().add(photo);
    apartamentRepository.save(apartament);
    return repository.findById(photoId).get();
  }

  @Transactional
  public Photo deletePhotoForApartament(Integer apartamentId, Integer photoId) {
    if (!apartamentRepository.existsById(apartamentId)) {
      throw new NoSuchApartamentException();
    }
    if (!repository.existsById(photoId)) {
      throw new NoSuchPhotoException();
    }
    Apartament apartament = apartamentRepository.findById(apartamentId).get();
    Photo photo = repository.findById(photoId).get();

    if (photo.getApartaments().isEmpty()) {
      throw new NoSuchRelationApartamentPhotoException();
    }

    apartament.getPhotos().remove(photo);
    apartamentRepository.save(apartament);
    return repository.findById(photoId).get();
  }

  public Set<Photo> getPhotosByResponseId(Integer responseId) {
    if (responseRepository.existsById(responseId)) {
      Response response = responseRepository.findById(responseId).get();
      return response.getPhotos();
    }
    throw new NoSuchResponseException();
  }

  @Transactional
  public Photo setPhotoForResponse(Integer responseId, Integer photoId) {
    if (!repository.existsById(photoId)) {
      throw new NoSuchPhotoException();
    }
    if (!responseRepository.existsById(responseId)) {
      throw new NoSuchResponseException();
    }
    Response response = responseRepository.findById(responseId).get();
    Photo photo = repository.findById(photoId).get();

    if (!photo.getResponses().isEmpty()) {
      throw new AlreadyExistRelationResponsePhotoException();
    }

    response.getPhotos().add(photo);
    responseRepository.save(response);
    return repository.findById(photoId).get();
  }

  @Transactional
  public Photo deletePhotoForResponse(Integer responseId, Integer photoId) {
    if (!repository.existsById(photoId)) {
      throw new NoSuchPhotoException();
    }
    if (!responseRepository.existsById(responseId)) {
      throw new NoSuchResponseException();
    }
    Response response = responseRepository.findById(responseId).get();
    Photo photo = repository.findById(photoId).get();

    if (photo.getResponses().isEmpty()) {
      throw new NoSuchRelationResponsePhotoException();
    }

    response.getPhotos().remove(photo);
    responseRepository.save(response);
    return repository.findById(photoId).get();
  }
}
