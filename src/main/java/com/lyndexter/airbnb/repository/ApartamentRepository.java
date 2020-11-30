package com.lyndexter.airbnb.repository;

import com.lyndexter.airbnb.model.Apartament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartamentRepository extends JpaRepository<Apartament, Integer> {}
