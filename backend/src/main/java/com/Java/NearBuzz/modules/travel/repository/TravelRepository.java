package com.Java.NearBuzz.modules.travel.repository;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.modules.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByStatusOrderByCreatedAtDesc(ListingStatus status);
}
