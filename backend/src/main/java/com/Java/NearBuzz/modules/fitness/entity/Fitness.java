package com.Java.NearBuzz.modules.fitness.entity;

import com.Java.NearBuzz.common.entity.BaseListing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fitness_sessions")
@Getter
@Setter
public class Fitness extends BaseListing {

    @Column(name = "trainer_name")
    private String trainerName;

    @Column(name = "difficulty_level")
    private String difficultyLevel;
}
