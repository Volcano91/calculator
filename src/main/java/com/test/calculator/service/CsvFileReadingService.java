package com.test.calculator.service;

import com.test.calculator.exceptions.FileException;
import com.test.calculator.model.Record;
import com.test.calculator.transformer.RecordTransformer;
import com.test.calculator.csv_record.filter.CsvRecordFilterChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@Component
public class CsvFileReadingService {

    private static final int PILL_NUMBER = 150;
    private static final Record POISON_PILL = Record.builder().build();

    private final RecordQueue recordsQueue;

    private final CsvRecordFilterChain recordFilterChain;

    private final RecordTransformer recordTransformer;

    public CsvFileReadingService(RecordQueue recordsQueue,
                                 CsvRecordFilterChain recordFilterChain,
                                 RecordTransformer recordTransformer) {
        this.recordsQueue = recordsQueue;
        this.recordFilterChain = recordFilterChain;
        this.recordTransformer = recordTransformer;
    }

    public void readRecords(File file) throws FileException {

        try {

            CSVParser csvParser = CSVParser.parse(file, Charset.defaultCharset(),
                    CSVFormat.RFC4180 .withDelimiter(';'));
            parseRecords(csvParser);

        } catch (InterruptedException | IOException e) {
            log.error("Error while parsing the file. " + e.getMessage());
            Thread.currentThread().interrupt();
        }

    }

    private void parseRecords(CSVParser csvParser) throws InterruptedException {

        for (CSVRecord csvRecord : csvParser) {
            if(!recordFilterChain.filter(csvRecord)) {
                continue;
            }

            Record record = recordTransformer.transform(csvRecord);
            recordsQueue.put(record);
        }

        putPoisonPills();
    }

    private void putPoisonPills() throws InterruptedException {
        for (int j = 0; j < PILL_NUMBER; j++) {
            recordsQueue.put(POISON_PILL);
        }
    }

}
