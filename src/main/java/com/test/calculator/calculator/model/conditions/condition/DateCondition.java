package com.test.calculator.calculator.model.conditions.condition;

import com.test.calculator.model.Record;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DateCondition implements Condition {

    private String dateCondition;

    @Override
    public boolean validateRecord(Record record) {

        return !record.getDate().contains(dateCondition) ? false : true;

    }
}
