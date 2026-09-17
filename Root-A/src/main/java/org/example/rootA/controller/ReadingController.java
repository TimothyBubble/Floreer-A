package org.example.rootA.controller;

import jakarta.validation.Valid;
import org.example.rootA.model.Plant;
import org.example.rootA.model.Reading;
import org.example.rootA.repository.ReadingRepository;
import org.example.rootA.repository.PlantRepository;
import org.example.rootA.service.WateringLogicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    private final ReadingRepository readingRepository;
    private final PlantRepository plantRepository;
    private final WateringLogicService wateringLogicService;

    public ReadingController(ReadingRepository readingRepository, PlantRepository plantRepository, WateringLogicService wateringLogicService) {
        this.readingRepository = readingRepository;
        this.plantRepository = plantRepository;
        this.wateringLogicService = wateringLogicService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reading> getReading(@PathVariable Long id) {
        return readingRepository.findById(id)
                .map(reading -> ResponseEntity.ok(reading))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Reading> createReading(@Valid @RequestBody Reading reading) {
        Reading saved = readingRepository.save(reading);

        Instant cutoff = Instant.now().minus(5, ChronoUnit.MINUTES);

        List<Reading> recentReadings = readingRepository.findByPlantIdAndTimeStampAfter(saved.getPlant().getId(), cutoff);

        boolean allDry = wateringLogicService.needsWater(recentReadings);

        Plant plant = plantRepository.findById(saved.getPlant().getId())
                .orElseThrow(() -> new RuntimeException("Plant not found"));
        plant.setNeedsWater(allDry);
        plantRepository.save(plant);

        saved.setPlant(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(@PathVariable Long id) {
        readingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
