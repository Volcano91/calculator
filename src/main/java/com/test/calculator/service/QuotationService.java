package com.test.calculator.service;

import com.test.calculator.tasks.RecordQueue;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {

    private final RecordQueue recordsQueue;

    private final FileReaderService fileReaderService;

    private final RecordProcessorService recordProcessorService;

    public QuotationService(RecordQueue recordsQueue,
                            FileReaderService fileReaderService,
                            RecordProcessorService recordProcessorService) {
        this.recordsQueue = recordsQueue;
        this.fileReaderService = fileReaderService;
        this.recordProcessorService = recordProcessorService;
    }

    public void doCalculations() {
        fileReaderService.readQuotationsFile();
        recordProcessorService.processQuotations();
    }
}
