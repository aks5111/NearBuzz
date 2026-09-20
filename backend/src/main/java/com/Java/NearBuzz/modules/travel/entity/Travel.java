package com.Java.NearBuzz.modules.travel.entity;

import com.Java.NearBuzz.common.entity.BaseListing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "travel_packages")
@Getter
@Setter
public class Travel extends BaseListing {

    @Column(name = "duration_days")
    private Integer durationDays;

    @Column(name = "group_size")
    private Integer groupSize;

    @Column(columnDefinition = "TEXT")
    private String inclusions;

    @Column(name = "agency_name", length = 150)
    private String agencyName;

    @Column(name = "agency_contact_name", length = 150)
    private String agencyContactName;

    @Column(name = "agency_phone", length = 30)
    private String agencyPhone;

    @Column(name = "agency_email", length = 150)
    private String agencyEmail;

    @Column(name = "agency_photo_url", length = 500)
    private String agencyPhotoUrl;
}
