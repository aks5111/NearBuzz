package com.Java.NearBuzz.modules.eventbooking.entity;

import com.Java.NearBuzz.common.entity.BaseListing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
public class EventBooking extends BaseListing {

    @Column(name = "organizer_name")
    private String organizerName;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @Column(name = "seats_booked")
    private Integer seatsBooked = 0;
}
