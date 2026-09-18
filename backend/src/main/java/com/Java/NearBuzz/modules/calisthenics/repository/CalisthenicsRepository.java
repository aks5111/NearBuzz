package com.Java.NearBuzz.modules.calisthenics.repository;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.modules.calisthenics.entity.Calisthenics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalisthenicsRepository extends JpaRepository<Calisthenics, Long> {
    List<Calisthenics> findByStatusOrderByCreatedAtDesc(ListingStatus status);
}
