package com.lyndexter.airbnb.controller;

import java.util.List;

public interface ControllerWithDto<T, S> {
  List<T> createDtos(Iterable<S> entities);

  T createDto(S entity);
}
