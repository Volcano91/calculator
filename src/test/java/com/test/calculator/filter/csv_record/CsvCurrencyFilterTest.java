package com.test.calculator.filter.csv_record;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static com.test.calculator.TestModel.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CsvCurrencyFilterTest {

    private CsvCurrencyFilter currencyFilter;

    @Before
    public void setUp() {
        currencyFilter = new CsvCurrencyFilter();
    }

    @Test
    public void should_filter_currency_other_than_pln() throws IOException {
        //GIVEN
        CSVParser parser = getParserForFile(WRONG_CURRENCY_RESOURCE);
        boolean isValid = false;

        //WHEN
        for (CSVRecord record : parser) {
            isValid = currencyFilter.filter(record);
        }

        //THEN
        assertThat(isValid).isFalse();
    }
}