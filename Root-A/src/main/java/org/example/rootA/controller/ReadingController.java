package org.example.rootA.controller;

import jakarta.validation.Valid;
import org.example.rootA.model.Reading;
import org.example.rootA.repository.ReadingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    private final ReadingRepository readingRepository;

    public ReadingController(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @GetMapping
    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Reading> createReading(@Valid @RequestBody Reading reading) {
        Reading saved = readingRepository.save(reading);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(@PathVariable long id) {
        readingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
