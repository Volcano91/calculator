package com.test.calculator.calculator.model.conditions.condition_chain;

import com.test.calculator.calculator.model.conditions.condition.Condition;
import com.test.calculator.model.Record;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrConditionChain implements ConditionChain {

    private List<Condition> conditions;

    @Override
    public boolean validate(Record record) {
        boolean result = false;

        for (Condition condition : conditions) {
            if(condition.validateRecord(record)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
