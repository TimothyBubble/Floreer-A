package org.example.rootA.model;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SensorType type;

    private String description;
    private String version;

    @NotNull
    private Instant createdAt;

    // Required by JPA/Hibernate
    public Sensor() {}

    public Sensor(String name, SensorType type, String description, String version) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.version = version;
        this.createdAt = Instant.now();
    }

    @ManyToOne
    private Plant plant;
}
