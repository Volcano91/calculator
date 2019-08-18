package com.test.calculator.csv_record.filter;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvCurrencyFilter implements CsvFilter {

    private static final String CURRENCY_TO_FILTER = "PLN";
    private static final int CURRENCY_INDEX = 2;

    private CsvFilter nextFilter;

    @Override
    public boolean filter(CSVRecord record) {

        return record.get(CURRENCY_INDEX).equals(CURRENCY_TO_FILTER)
                ? nextFilter != null
                ? nextFilter.filter(record)
                : true
                : false;
    }

    @Override
    public void setNext(CsvFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
}
