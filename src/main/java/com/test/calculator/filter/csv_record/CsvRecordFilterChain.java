package com.test.calculator.filter.csv_record;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CsvRecordFilterChain {

    private List<CsvFilter> csvFilters;

    public CsvRecordFilterChain(List<CsvFilter> csvFilters) {
        this.csvFilters = csvFilters;
    }

    public boolean filter(CSVRecord csvRecord) {
        for (int i = 0; i < csvFilters.size() - 1; i++) {
            csvFilters.get(i).setNext(csvFilters.get(i + 1));
        }

        return csvFilters.get(0).filter(csvRecord);
    }
}
