package org.example.expert.domain.log.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.log.entity.Log;
import org.example.expert.domain.log.repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    @Transactional(propagation = REQUIRES_NEW)
    public void save(String message) {
        Log log = new Log(message);
        logRepository.save(log);
    }
}
