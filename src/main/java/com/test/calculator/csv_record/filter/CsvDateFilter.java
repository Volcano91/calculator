package com.test.calculator.csv_record.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@Slf4j
public class CsvDateFilter implements CsvFilter {

    private static final int DATE_INDEX = 0;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private CsvFilter nextFilter;

    @Override
    public boolean filter(CSVRecord record) {
        try {
            String date = record.get(DATE_INDEX);
            LocalDate dateTime = LocalDate.parse(date, FORMATTER);

            return !dateTime.format(FORMATTER).equals(date)
                    ? false
                    : nextFilter != null
                    ? nextFilter.filter(record)
                    : true;
        }
        catch (DateTimeParseException ex) {
            log.error("Record has an incorrect date format. Message: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public void setNext(CsvFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
}
