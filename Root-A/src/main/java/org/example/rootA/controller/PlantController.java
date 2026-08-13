package org.example.rootA.controller;

import org.example.rootA.model.Plant;
import org.example.rootA.repository.PlantRepository;
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
    public Plant createPlant(@RequestBody Plant plant) {
        return plantRepository.save(plant);
    }
}