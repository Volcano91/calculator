package com.test.calculator.tasks;

import com.test.calculator.model.Record;
import com.test.calculator.transformer.RecordTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@Component
public class CsvFileReadingTask {

    @NotNull
    private File stocksFile;

    private static final int PILL_NUMBER = 150;
    private static final Record POISON_PILL = Record.builder().build();

    private final RecordQueue recordsQueue;

    private final RecordTransformer recordTransformer;

    public CsvFileReadingTask(RecordQueue recordsQueue,
                              RecordTransformer recordTransformer) {
        this.recordsQueue = recordsQueue;
        this.recordTransformer = recordTransformer;
    }

    public void readRecords(String fileName) {

        try {
            CSVParser csvParser = getCsvRecordsFromFile(fileName);
            parseRecords(csvParser);

        } catch (InterruptedException | IOException ex) {
            log.error("Error while reading the file. Message: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private CSVParser getCsvRecordsFromFile(String fileName) throws IOException {
        stocksFile = ResourceUtils.getFile(fileName);

        return CSVParser.parse(stocksFile, Charset.defaultCharset(), CSVFormat.RFC4180
                .withDelimiter(';'));
    }

    private void parseRecords(CSVParser csvParser) throws InterruptedException {

        for (CSVRecord csvRecord : csvParser) {
            Record record = recordTransformer.transform(csvRecord);
            recordsQueue.put(record);
            System.out.println("Record put: " + csvRecord.getRecordNumber());
        }

        putPoisonPills();
    }

    private void putPoisonPills() throws InterruptedException {
        for (int j = 0; j < PILL_NUMBER; j++) {
            recordsQueue.put(POISON_PILL);
        }
    }

}
