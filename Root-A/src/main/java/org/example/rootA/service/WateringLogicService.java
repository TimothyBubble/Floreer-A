package org.example.rootA.service;

import org.example.rootA.model.Reading;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WateringLogicService {

    public boolean needsWater(List<Reading>recentReadings)
    {
        if(recentReadings.size() < 5) {
            return false;
        }
        return recentReadings.stream()
                .allMatch(r -> r.getValue() < 30.0);
    }
}
