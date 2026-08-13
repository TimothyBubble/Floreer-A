package org.example.rootA.controller;

import jakarta.validation.Valid;
import org.example.rootA.model.Plant;
import org.example.rootA.repository.PlantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantRepository plantRepository;

    public PlantController(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Plant> createPlant(@Valid @RequestBody Plant plant) {
        Plant saved = plantRepository.save(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}