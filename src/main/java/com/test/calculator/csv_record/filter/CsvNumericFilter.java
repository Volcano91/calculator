package com.test.calculator.csv_record.filter;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CsvNumericFilter implements CsvFilter {

    private static final int MINUS_ONE = -1;

    private CsvFilter nextFilter;

    @Override
    public void setNext(CsvFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public boolean filter(CSVRecord csvRecord) {

        for (int i = 3; i < csvRecord.size(); i++) {
            String value = csvRecord.get(i);

            if(NumberUtils.isNumber(value)) {
                if(isValueNegative(value)) {
                    return false;
                };
            }
            else {
                return false;
            }
        }

        return nextFilter != null ? nextFilter.filter(csvRecord) : true;
    }

    private boolean isValueNegative(String value) {
        BigDecimal numericValue = NumberUtils.createBigDecimal(value);

        return numericValue.compareTo(BigDecimal.ZERO) == MINUS_ONE;
    }
}
