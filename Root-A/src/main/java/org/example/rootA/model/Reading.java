package org.example.rootA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double value;

    @NotNull
    private Instant timeStamp;

    @NotNull
    @ManyToOne
    private Sensor sensor;

    @NotNull
    @ManyToOne
    private Plant plant;

    public Reading() {}

    public Reading(Double value, Instant timeStamp, Sensor sensor, Plant plant) {
        this.value = value;
        this.timeStamp = timeStamp;
        this.sensor = sensor;
        this.plant = plant;
    }
}
