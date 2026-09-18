package com.Java.NearBuzz.modules.calisthenics.entity;

import com.Java.NearBuzz.common.entity.BaseListing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "calisthenics_sessions")
@Getter
@Setter
public class Calisthenics extends BaseListing {

    @Column(name = "difficulty_level")
    private String difficultyLevel;
}
