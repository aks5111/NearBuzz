package com.Java.NearBuzz.modules.friendsnearby.repository;

import com.Java.NearBuzz.common.entity.ListingStatus;
import com.Java.NearBuzz.modules.friendsnearby.entity.FriendMeetup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendMeetupRepository extends JpaRepository<FriendMeetup, Long> {
    List<FriendMeetup> findByStatusOrderByCreatedAtDesc(ListingStatus status);
}
