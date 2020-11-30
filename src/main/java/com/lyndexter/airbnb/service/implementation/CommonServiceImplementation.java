package com.lyndexter.airbnb.service.implementation;

import com.lyndexter.airbnb.service.CommonService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class CommonServiceImplementation<T, ID> implements CommonService<T, ID> {

  protected abstract JpaRepository<T, ID> getRepository();

  protected abstract void throwExeption();

  protected abstract T mergeEntities(T newEntity, T entity);

  protected abstract void checkIfEmpty(T entity);

  @Override
  public List<T> getEntities() {
    return getRepository().findAll();
  }

  @Override
  public T getEntity(ID entityId) {
    if (getRepository().existsById(entityId)) {
      return getRepository().findById(entityId).get();
    }
    throwExeption();
    return null;
  }

  @Override
  public T createEntity(T entity) {
    return getRepository().save(entity);
  }

  @Override
  public T updateEntity(T entity, ID entityId) {

    if (getRepository().existsById(entityId)) {
      T newEntity = getRepository().findById(entityId).get();

      return getRepository().save(mergeEntities(newEntity, entity));
    }
    throwExeption();
    return null;
  }

  @Override
  public void deleteEntity(ID entityId) {
    if (getRepository().existsById(entityId)) {
      getRepository().deleteById(entityId);
      return;
    }
    throwExeption();
  }
}
