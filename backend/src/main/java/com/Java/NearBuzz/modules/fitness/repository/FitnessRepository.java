package com.Java.NearBuzz.modules.fitness.repository;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.modules.fitness.entity.Fitness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FitnessRepository extends JpaRepository<Fitness, Long> {
    List<Fitness> findByStatusOrderByCreatedAtDesc(ListingStatus status);
}
