package com.test.calculator.csv_record.filter;

import org.apache.commons.csv.CSVRecord;

public interface CsvFilter {

    boolean filter(CSVRecord record);

    void setNext(CsvFilter nextFilter);

}
