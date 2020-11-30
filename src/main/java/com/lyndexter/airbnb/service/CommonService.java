package com.lyndexter.airbnb.service;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public interface CommonService<T, ID> {

  List<T> getEntities();

  T getEntity(ID entityId);

  @Transactional
  T createEntity(T entity);

  @Transactional
  T updateEntity(T entity, ID entityId);

  @Transactional
  void deleteEntity(ID entityId);
}
