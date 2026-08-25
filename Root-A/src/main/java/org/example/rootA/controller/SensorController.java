package org.example.rootA.controller;

import jakarta.validation.Valid;
import org.example.rootA.model.Sensor;
import org.example.rootA.repository.SensorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable Long id) {
        return sensorRepository.findById(id)
                .map(sensor -> ResponseEntity.ok(sensor))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Sensor> createSensor(@Valid @RequestBody Sensor sensor) {
        Sensor saved = sensorRepository.save(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}