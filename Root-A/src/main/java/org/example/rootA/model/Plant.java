package org.example.rootA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String species;
    private String description;
    private boolean needsWater;

    @NotNull
    private Instant createdAt;

    // Required by JPA/Hibernate
    public Plant() {}

    public Plant(String name, String species, String description) {
        this.name = name;
        this.species = species;
        this.description = description;
        this.needsWater = false;
        this.createdAt = Instant.now();
    }
}