package org.example.rootA.controller;

import jakarta.validation.Valid;
import org.example.rootA.model.Plant;
import org.example.rootA.model.Reading;
import org.example.rootA.repository.ReadingRepository;
import org.example.rootA.repository.PlantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    private final ReadingRepository readingRepository;
    private final PlantRepository plantRepository;

    public ReadingController(ReadingRepository readingRepository, PlantRepository plantRepository) {
        this.readingRepository = readingRepository;
        this.plantRepository = plantRepository;
    }

    @GetMapping
    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Reading> createReading(@Valid @RequestBody Reading reading) {
        Reading saved = readingRepository.save(reading);

        List<Reading> recentReadings = readingRepository.findTop5ByPlantIdOrderByTimeStampDesc(saved.getPlant().getId());

        boolean allDry = recentReadings.stream()
                .allMatch(r -> r.getValue() < 30.0);

        Plant plant = plantRepository.findById(saved.getPlant().getId())
                .orElseThrow(() -> new RuntimeException("Plant not found"));
        plant.setNeedsWater(allDry);
        plantRepository.save(plant);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(@PathVariable Long id) {
        readingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
