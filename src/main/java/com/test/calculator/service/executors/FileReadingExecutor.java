package com.test.calculator.service.executors;

import com.test.calculator.service.CsvFileReadingService;
import com.test.calculator.service.RecordQueue;
import com.test.calculator.transformer.RecordTransformer;
import com.test.calculator.validator.file.FileValidatorChain;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileReadingExecutor {

    private final ExecutorService readerService = Executors.newSingleThreadExecutor();
    private final RecordQueue recordQueue;
    private final RecordTransformer recordTransformer;
    private final FileValidatorChain validatorChain;
    private CsvFileReadingService csvFileReadingService;

    public FileReadingExecutor(RecordQueue recordQueue, RecordTransformer recordTransformer,
                               FileValidatorChain validatorChain,
                               CsvFileReadingService csvFileReadingService) {
        this.recordQueue = recordQueue;
        this.recordTransformer = recordTransformer;
        this.validatorChain = validatorChain;
        this.csvFileReadingService = csvFileReadingService;
    }

    public void readQuotationsFile(String fileName) {
        File stocksFile = validatorChain.validate(fileName);

        readerService.submit(() -> csvFileReadingService.readRecords(stocksFile));

        readerService.shutdown();
    }
}
