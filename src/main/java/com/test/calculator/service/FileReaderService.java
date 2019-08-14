package com.test.calculator.service;

import com.test.calculator.tasks.CsvFileReadingTask;
import com.test.calculator.tasks.RecordQueue;
import com.test.calculator.transformer.RecordTransformer;
import com.test.calculator.validator.file.FileValidator;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileReaderService {

    private final ExecutorService readerService = Executors.newSingleThreadExecutor();
    private final RecordQueue recordQueue;
    private final RecordTransformer recordTransformer;
    private final FileValidator validator;

    public FileReaderService(RecordQueue recordQueue, RecordTransformer recordTransformer,
                             FileValidator validator) {
        this.recordQueue = recordQueue;
        this.recordTransformer = recordTransformer;
        this.validator = validator;
    }

    public void readQuotationsFile(String fileName) {
        CsvFileReadingTask readingTask = new CsvFileReadingTask(recordQueue, recordTransformer);
        readerService.submit(() -> readingTask.readRecords(fileName));

        readerService.shutdown();
    }
}
