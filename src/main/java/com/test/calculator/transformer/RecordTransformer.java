package com.test.calculator.transformer;

import com.test.calculator.model.Record;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class RecordTransformer {

    public Record transform(CSVRecord csvRecord) {
        return Record.builder()
                .date(csvRecord.get(0))
                .isin(csvRecord.get(1))
                .currency(csvRecord.get(2))
                .price(csvRecord.get(3))
                .volume(csvRecord.get(4))
                .turnOver(csvRecord.get(5))
                .build();
    }
}
