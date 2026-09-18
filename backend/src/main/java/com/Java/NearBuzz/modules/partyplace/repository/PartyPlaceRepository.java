package com.Java.NearBuzz.modules.partyplace.repository;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.modules.partyplace.entity.PartyPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyPlaceRepository extends JpaRepository<PartyPlace, Long> {
    List<PartyPlace> findByStatusOrderByCreatedAtDesc(ListingStatus status);
}
