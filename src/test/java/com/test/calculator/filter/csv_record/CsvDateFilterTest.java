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
public class CsvDateFilterTest {

    private CsvDateFilter dateValidator;

    @Before
    public void setUp() {
        dateValidator = new CsvDateFilter();
    }

    @Test
    public void should_return_true_on_correct_date() throws IOException {
        //GIVEN
        CSVParser parser = getParserForFile(RESOURCE_LOCATION);
        boolean isValid = false;

        //WHEN
        for(CSVRecord record : parser) {
            isValid = dateValidator.filter(record);
        }

        //THEN
        assertThat(isValid).isTrue();
    }

    @Test
    public void should_return_false_on_wrong_date() throws IOException {
        //GIVEN
        CSVParser parser = getParserForFile(WRONG_DATE_RESOURCE);
        boolean isValid = false;

        //WHEN
        for(CSVRecord record : parser) {
            isValid = dateValidator.filter(record);
        }

        //THEN
        assertThat(isValid).isFalse();
    }
}