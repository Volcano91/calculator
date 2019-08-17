package com.test.calculator.calculator.validator;

import com.test.calculator.calculator.model.conditions.condition_chain.ConditionChain;
import com.test.calculator.model.Record;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConditionValidator {

    public boolean validate(List<ConditionChain> conditionChains, Record record) {
        boolean result = true;

        for (ConditionChain conditionChain : conditionChains) {
            if(!conditionChain.validate(record)) {
                result = false;
            }
        }

        return result;
    }

}

