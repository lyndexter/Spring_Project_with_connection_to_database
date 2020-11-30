package com.lyndexter.airbnb.repository;

import com.lyndexter.airbnb.model.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {}
