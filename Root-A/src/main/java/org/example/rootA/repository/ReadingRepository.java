package org.example.rootA.repository;

import org.example.rootA.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
}