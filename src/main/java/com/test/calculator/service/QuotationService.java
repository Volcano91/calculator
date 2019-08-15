package com.test.calculator.service;

import com.test.calculator.service.executors.FileReadingExecutor;
import com.test.calculator.service.executors.RecordProcessingExecutor;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {

    private final RecordQueue recordsQueue;

    private final FileReadingExecutor fileReadingExecutor;

    private final RecordProcessingExecutor recordProcessingExecutor;

    public QuotationService(RecordQueue recordsQueue,
                            FileReadingExecutor fileReadingExecutor,
                            RecordProcessingExecutor recordProcessingExecutor) {
        this.recordsQueue = recordsQueue;
        this.fileReadingExecutor = fileReadingExecutor;
        this.recordProcessingExecutor = recordProcessingExecutor;
    }

    public void doCalculations(String fileName) {

        fileReadingExecutor.readQuotationsFile(fileName);
        recordProcessingExecutor.processQuotations();

    }
}
