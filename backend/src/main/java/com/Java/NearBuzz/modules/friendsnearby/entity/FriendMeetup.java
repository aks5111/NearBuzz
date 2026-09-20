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

    @Column(name = "venue_name", length = 150)
    private String venueName;

    @Column(name = "host_name", length = 150)
    private String hostName;

    @Column(name = "host_phone", length = 30)
    private String hostPhone;

    @Column(name = "host_email", length = 150)
    private String hostEmail;

    @Column(name = "host_photo_url", length = 500)
    private String hostPhotoUrl;
}
