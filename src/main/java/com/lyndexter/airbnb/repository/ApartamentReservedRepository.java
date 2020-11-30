package com.lyndexter.airbnb.repository;

import com.lyndexter.airbnb.model.ApartamentReserved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartamentReservedRepository extends JpaRepository<ApartamentReserved, Integer> {}
