package com.test.calculator.calculator.model.conditions.condition;

import com.test.calculator.model.Record;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ISINCondition implements Condition {

    private String condition;

    @Override
    public boolean validateRecord(Record record) {

        return !record.getIsin().equals(condition) ? false : true;

    }
}
