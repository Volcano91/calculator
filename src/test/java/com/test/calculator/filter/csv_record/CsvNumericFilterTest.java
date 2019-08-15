package com.test.calculator.filter.csv_record;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.test.calculator.TestModel.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CsvNumericFilterTest {

    private CsvNumericFilter numericValidator;

    @Before
    public void setUp() {
        numericValidator = new CsvNumericFilter();
    }

    @Test
    public void should_return_false_if_value_is_incorrect() throws IOException {
        //GIVEN
        CSVParser parser = getParserForFile(NON_NUMERIC_RESOURCE);
        List<Boolean> actual = new ArrayList<>();

        //WHEN
        for(CSVRecord record : parser) {
            actual.add(numericValidator.filter(record));
        }

        //THEN
        assertThat(actual).containsExactly(false, false);
    }

    @Test
    public void should_return_true_if_value_is_correct() throws IOException {
        //GIVEN
        CSVParser parser = getParserForFile(RESOURCE_LOCATION);
        boolean isValid = false;

        //WHEN
        for(CSVRecord record : parser) {
            isValid = numericValidator.filter(record);
        }

        //THEN
        assertThat(isValid).isTrue();
    }
}