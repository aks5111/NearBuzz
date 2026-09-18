package com.Java.NearBuzz.modules.partyplace.entity;

import com.Java.NearBuzz.common.entity.BaseListing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "party_places")
@Getter
@Setter
public class PartyPlace extends BaseListing {

    @Column(name = "capacity")
    private Integer capacity;
}
