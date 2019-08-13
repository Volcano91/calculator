package com.test.calculator.service;

import com.test.calculator.tasks.CsvFileReadingTask;
import com.test.calculator.tasks.RecordQueue;
import com.test.calculator.transformer.RecordTransformer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileReaderService {

    private final ExecutorService readerService = Executors.newSingleThreadExecutor();

    private final RecordQueue recordQueue;

    private final RecordTransformer recordTransformer;

    public FileReaderService(RecordQueue recordQueue, RecordTransformer recordTransformer) {
        this.recordQueue = recordQueue;
        this.recordTransformer = recordTransformer;
    }

    public void readQuotationsFile() {
        readerService.submit(new CsvFileReadingTask(recordQueue, recordTransformer));

        readerService.shutdown();
    }
}
