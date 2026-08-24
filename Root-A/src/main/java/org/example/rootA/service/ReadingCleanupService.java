package org.example.rootA.service;

import jakarta.transaction.Transactional;
import org.example.rootA.repository.ReadingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class ReadingCleanupService {

    private final ReadingRepository readingRepository;

    public ReadingCleanupService(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Transactional
    @Scheduled(cron = "0 0 3 * * *")
    public void deleteOldReadings() {
        Instant cutoff = Instant.now().minus(90, ChronoUnit.DAYS);
        readingRepository.deleteByTimeStampBefore(cutoff);
    }
}
