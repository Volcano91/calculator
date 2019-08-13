package com.test.calculator.tasks;

import com.test.calculator.model.Record;
import com.test.calculator.transformer.RecordTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@Component
public class CsvFileReadingTask implements Runnable {

    private static final String FILE_NAME = "classpath:csv/Stocks.csv";

    private static final int PILL_NUMBER = 50;

    private static final Record POISON_PILL = Record.builder().build();

    private final RecordQueue recordsQueue;

    private final RecordTransformer recordTransformer;

    public CsvFileReadingTask(RecordQueue recordsQueue, RecordTransformer recordTransformer) {
        this.recordsQueue = recordsQueue;
        this.recordTransformer = recordTransformer;
    }

    @Override
    public void run() {
        try {
            File stocksFile = ResourceUtils.getFile(FILE_NAME);

            CSVParser csvParser = CSVParser.parse(stocksFile, Charset.defaultCharset(), CSVFormat.RFC4180
                    .withDelimiter(';'));

            for (CSVRecord csvRecord : csvParser) {
                Record record = recordTransformer.transform(csvRecord);
                recordsQueue.put(record);
                System.out.println("Record put: " + csvRecord.getRecordNumber());
            }

            for (int j = 0; j < PILL_NUMBER; j++) {
                recordsQueue.put(POISON_PILL);
            }

        } catch (InterruptedException | IOException e) {
            log.error("Error while reading the file. Message: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
