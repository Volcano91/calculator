package com.test.calculator.service;

import com.test.calculator.tasks.RecordProcessingTask;
import com.test.calculator.tasks.RecordQueue;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RecordProcessorService {

    private static final int THREAD_COUNT = 150;

    private final ExecutorService processorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private final RecordQueue recordQueue;
    private final RecordProcessingTask processingTask;

    public RecordProcessorService(RecordQueue recordQueue, RecordProcessingTask processingTask) {
        this.recordQueue = recordQueue;
        this.processingTask = processingTask;
    }

    public void processQuotations() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            processorService.submit(processingTask);
        }

        processorService.shutdown();
    }
}
