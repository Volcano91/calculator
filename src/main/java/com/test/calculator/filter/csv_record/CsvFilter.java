package com.test.calculator.filter.csv_record;

import org.apache.commons.csv.CSVRecord;

public interface CsvFilter {

    boolean filter(CSVRecord record);

    void setNext(CsvFilter nextFilter);

}
