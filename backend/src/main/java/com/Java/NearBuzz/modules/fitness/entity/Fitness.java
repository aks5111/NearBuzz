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

    @Column(name = "gym_name", length = 150)
    private String gymName;

    @Column(name = "trainer_phone", length = 30)
    private String trainerPhone;

    @Column(name = "trainer_email", length = 150)
    private String trainerEmail;

    @Column(name = "trainer_photo_url", length = 500)
    private String trainerPhotoUrl;
}
