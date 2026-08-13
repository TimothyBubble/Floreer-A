package org.example.rootA.controller;

import org.example.rootA.model.Reading;
import org.example.rootA.repository.ReadingRepository;
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
    public Reading createReading(@RequestBody Reading reading) {
        return readingRepository.save(reading);
    }

    @DeleteMapping("/{id}")
    public void deleteReading(@PathVariable long id) {
        readingRepository.deleteById(id);
    }
}
