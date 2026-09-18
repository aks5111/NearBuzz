package com.Java.NearBuzz.modules.friendsnearby.entity;

import com.Java.NearBuzz.common.entity.BaseListing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "friend_meetups")
@Getter
@Setter
public class FriendMeetup extends BaseListing {

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "max_participants")
    private Integer maxParticipants;
}
