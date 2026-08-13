package org.example.rootA.controller;

import org.example.rootA.model.Sensor;
import org.example.rootA.repository.SensorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable Long id) {
        sensorRepository.deleteById(id);
    }
}