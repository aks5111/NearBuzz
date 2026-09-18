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
}
