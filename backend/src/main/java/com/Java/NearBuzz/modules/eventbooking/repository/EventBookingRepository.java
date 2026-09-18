package com.Java.NearBuzz.modules.eventbooking.repository;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.modules.eventbooking.entity.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
    List<EventBooking> findByStatusOrderByCreatedAtDesc(ListingStatus status);
}
