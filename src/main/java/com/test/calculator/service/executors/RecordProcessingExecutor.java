package com.test.calculator.service.executors;

import com.test.calculator.service.RecordQueue;
import com.test.calculator.service.RecordProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@Slf4j
public class RecordProcessingExecutor {

    private static final int THREAD_COUNT = 150;

    private final ExecutorService processorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private final RecordQueue recordQueue;
    private final RecordProcessingService processingService;

    public RecordProcessingExecutor(RecordQueue recordQueue, RecordProcessingService processingService) {
        this.recordQueue = recordQueue;
        this.processingService = processingService;
    }

    public ConcurrentHashMap processQuotations(ConcurrentHashMap resultsMap) {
        Future results = null;
        ConcurrentHashMap calculationMap = null;

        for (int i = 0; i < THREAD_COUNT; i++) {
           results =  processorService.submit(() -> processingService.processRecords(resultsMap));
        }

        processorService.shutdown();

        try {
           calculationMap = (ConcurrentHashMap) results.get();
        }
        catch (InterruptedException | ExecutionException e) {
            log.error("Error while processing a file: " + e.getMessage());
        }

        return calculationMap;
    }
}
