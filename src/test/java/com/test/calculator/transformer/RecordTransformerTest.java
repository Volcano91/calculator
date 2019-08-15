package com.test.calculator.transformer;

import com.test.calculator.model.Record;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDate;

import static com.test.calculator.TestModel.RESOURCE_LOCATION;
import static com.test.calculator.TestModel.getParserForFile;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RecordTransformerTest {

    private RecordTransformer transformer;

    @Before
    public void setUp() {
        transformer = new RecordTransformer();
    }

    @Test
    public void should_transform_csv_record_to_record() throws IOException {
        //GIVEN
        Record expected = Record.builder()
                                .number(1L)
                                .date(LocalDate.parse("2019-07-29"))
                                .isin("PLNFI0600010")
                                .currency("PLN")
                                .price("0.2")
                                .volume("0")
                                .turnOver("0")
                                .build();
        Record actual = null;
        CSVParser parser = getParserForFile(RESOURCE_LOCATION);

        //WHEN

        for (CSVRecord csvRecord : parser) {
            actual = transformer.transform(csvRecord);
        }

        //THEN
        assertThat(actual).isEqualTo(expected);
    }
}
