package org.example.rootA.repository;

import org.example.rootA.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    List<Reading> findTop5ByPlantIdOrderByTimeStampDesc(Long plantId);

    void deleteByTimeStampBefore(Instant cutoff);

    //List<Reading> findByPlantIdOrderByTimeStampDesc(Long plantId);
}