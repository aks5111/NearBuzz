package com.Java.NearBuzz.modules.sportsactivity.repository;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.modules.sportsactivity.entity.SportsActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportsActivityRepository extends JpaRepository<SportsActivity, Long> {
    List<SportsActivity> findByStatusOrderByCreatedAtDesc(ListingStatus status);
}
