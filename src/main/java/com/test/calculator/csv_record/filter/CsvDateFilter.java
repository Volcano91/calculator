package com.test.calculator.csv_record.filter;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class CsvDateFilter implements CsvFilter {

    private static String dateFormat = "yyyy-MM-dd";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    private CsvFilter nextFilter;

    @Override
    public boolean filter(CSVRecord record) {
        String date = record.get(0);
        try {
            LocalDate dateTime = LocalDate.parse(date, formatter);

            if(!dateTime.format(formatter).equals(date)) {
                return false;
            };
        }
        catch (DateTimeParseException ex) {
            return false;
        }

        return nextFilter != null ? nextFilter.filter(record) : true;
    }

    @Override
    public void setNext(CsvFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
}
