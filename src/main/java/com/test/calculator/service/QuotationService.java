package com.test.calculator.service;

import com.test.calculator.exceptions.FileException;
import com.test.calculator.tasks.RecordQueue;
import com.test.calculator.validator.file.FileValidator;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {

    private final RecordQueue recordsQueue;

    private final FileReaderService fileReaderService;

    private final RecordProcessorService recordProcessorService;

    private final FileValidator validator;

    public QuotationService(RecordQueue recordsQueue,
                            FileReaderService fileReaderService,
                            RecordProcessorService recordProcessorService,
                            FileValidator validator) {
        this.recordsQueue = recordsQueue;
        this.fileReaderService = fileReaderService;
        this.recordProcessorService = recordProcessorService;
        this.validator = validator;
    }

    public void doCalculations(String fileName) {
        if (validator.isValid(fileName)) {

            fileReaderService.readQuotationsFile(fileName);
            recordProcessorService.processQuotations();

        } else {
            throw new FileException("File extension differs from csv");
        }
    }
}
