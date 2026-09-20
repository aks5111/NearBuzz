package com.Java.NearBuzz.modules.sportsactivity.entity;

import com.Java.NearBuzz.common.entity.BaseListing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sports_activities")
@Getter
@Setter
public class SportsActivity extends BaseListing {

    @Column(name = "sport_type")
    private String sportType;

    @Column(name = "team_size")
    private Integer teamSize;

    @Column(name = "venue_name", length = 150)
    private String venueName;

    @Column(name = "organizer_name", length = 150)
    private String organizerName;

    @Column(name = "organizer_phone", length = 30)
    private String organizerPhone;

    @Column(name = "organizer_email", length = 150)
    private String organizerEmail;

    @Column(name = "organizer_photo_url", length = 500)
    private String organizerPhotoUrl;
}
