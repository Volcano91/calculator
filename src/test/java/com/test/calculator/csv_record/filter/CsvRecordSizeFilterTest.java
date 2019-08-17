package com.test.calculator.csv_record.filter;

import com.test.calculator.csv_record.filter.CsvRecordSizeFilter;
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
public class CsvRecordSizeFilterTest {

    private CsvRecordSizeFilter sizeValidator;

    @Before
    public void setUp() {
        sizeValidator = new CsvRecordSizeFilter();
    }

    @Test
    public void should_return_true_on_correct_size() throws IOException {
        //GIVEN
        CSVParser parser = getParserForFile(SIZES_RESOURCE);
        List<Boolean> actual = new ArrayList<>();

        //WHEN
        for(CSVRecord record : parser) {
           actual.add(sizeValidator.filter(record));
        }

        //THEN
        assertThat(actual).containsExactly(true, false);
    }
}
