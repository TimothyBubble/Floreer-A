package org.example.rootA.repository;

import org.example.rootA.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    // Alternative candidate for long term soil moisture wetness check
    List<Reading> findTop5ByPlantIdOrderByTimeStampDesc(Long plantId);

    // Currently used for testing
    // Retrieves all readings of a specific plant (by plantId)
    // and retrieves readings in the timespan of the last 5 minutes (cutoff)
    List<Reading> findByPlantIdAndTimeStampAfter(Long plantId, Instant cutoff);

    void deleteByTimeStampBefore(Instant cutoff);
}