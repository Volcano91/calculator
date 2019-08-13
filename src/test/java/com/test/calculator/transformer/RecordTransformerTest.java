package com.test.calculator.transformer;

import com.test.calculator.model.Record;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RecordTransformerTest {

    private static final String RESOURCE_LOCATION = "classpath:csv/test.csv";

    private RecordTransformer transformer;

    @Before
    public void setUp() {
        transformer = new RecordTransformer();
    }

    @Test
    public void should_transform_csv_record_to_record() throws IOException {
        //GIVEN
        Record expected = Record.builder()
                                .date("2019-07-29")
                                .isin("PLNFI0600010")
                                .currency("PLN")
                                .price("0.2")
                                .volume("0")
                                .turnOver("0")
                                .build();

        File testFile = ResourceUtils.getFile(RESOURCE_LOCATION);
        CSVParser parser = CSVParser.parse(testFile, Charset.defaultCharset(), CSVFormat.RFC4180.withDelimiter(';'));

        //WHEN
        Record actual = null;
        for (CSVRecord csvRecord : parser) {
            actual = transformer.transform(csvRecord);
        }

        //THEN
        assertThat(actual).isEqualTo(expected);
    }
}
