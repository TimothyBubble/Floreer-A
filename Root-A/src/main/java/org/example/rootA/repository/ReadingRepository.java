package org.example.rootA.repository;

import org.example.rootA.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    List<Reading> findTop5ByPlantIdOrderByTimeStampDesc(Long plantId);

    //List<Reading> findByPlantIdOrderByTimeStampDesc(Long plantId);
}