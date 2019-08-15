package com.test.calculator.filter.csv_record;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvCurrencyFilter implements CsvFilter {

    private CsvFilter nextFilter;

    @Override
    public boolean filter(CSVRecord record) {
        if(!record.get(2).equals("PLN")) {
            return false;
        }

        return nextFilter != null ? nextFilter.filter(record) : true;
    }

    @Override
    public void setNext(CsvFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
}
