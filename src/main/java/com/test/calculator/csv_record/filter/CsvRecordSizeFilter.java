package com.test.calculator.csv_record.filter;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvRecordSizeFilter implements CsvFilter {

    private static final int NEEDED_SIZE = 6;

    private CsvFilter nextFilter;

    @Override
    public boolean filter(CSVRecord record) {

        return record.size() != NEEDED_SIZE
                ? false
                : nextFilter != null
                ? nextFilter.filter(record)
                : true;
    }

    @Override
    public void setNext(CsvFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
}
