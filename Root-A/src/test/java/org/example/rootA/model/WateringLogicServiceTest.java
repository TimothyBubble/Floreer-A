package org.example.rootA.model;

import org.example.rootA.service.WateringLogicService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WateringLogicServiceTest {

    private final WateringLogicService service = new WateringLogicService();

    @Test
    void needsWater_whenAllReadingsAreDry_returnsTrue() {
        List<Reading> readings = List.of(
                new Reading(15.0, null, null, null),
                new Reading(20.0, null, null, null),
                new Reading(10.0, null, null, null),
                new Reading(25.0, null, null, null),
                new Reading(5.0, null, null, null)
        );

        assertTrue(service.needsWater(readings));
    }

    @Test
    void needsNoWater_whenAllReadingsAreWet_returnsFalse() {
        List<Reading> readings = List.of(
                new Reading(48.0, null, null, null),
                new Reading(46.0, null, null, null),
                new Reading(44.0, null, null, null),
                new Reading(42.0, null, null, null),
                new Reading(40.0, null, null, null)
        );

        assertFalse(service.needsWater(readings));
    }

    @Test
    void barelyNeedsWater_whenAllReadingsAreWet_returnsTrue() {
        List<Reading> readings = List.of(
                new Reading(29.9999, null, null, null),
                new Reading(29.9999, null, null, null),
                new Reading(29.9999, null, null, null),
                new Reading(29.9999, null, null, null),
                new Reading(29.9999, null, null, null)
        );

        assertTrue(service.needsWater(readings));
    }

    @Test
    void needsWater_whenFewerThan5ReadingsButAllDry_returnsFalse() {
        List<Reading> readings = List.of(
                new Reading(15.0, null, null, null),
                new Reading(17.0, null, null, null)
        );

        assertFalse(service.needsWater(readings));
    }
}
