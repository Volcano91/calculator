package com.test.calculator.service.executors;

import com.test.calculator.service.RecordQueue;
import com.test.calculator.tasks.RecordProcessingTask;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RecordProcessingExecutor {

    private static final int THREAD_COUNT = 150;

    private final ExecutorService processorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private final RecordQueue recordQueue;
    private final RecordProcessingTask processingService;

    public RecordProcessingExecutor(RecordQueue recordQueue, RecordProcessingTask processingService) {
        this.recordQueue = recordQueue;
        this.processingService = processingService;
    }

    public void processQuotations() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            processorService.submit(processingService);
        }

        processorService.shutdown();
    }
}
