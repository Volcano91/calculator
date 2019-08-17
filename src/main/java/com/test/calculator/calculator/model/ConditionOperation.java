package com.test.calculator.calculator.model;

import com.test.calculator.calculator.model.conditions.condition_chain.ConditionChain;
import com.test.calculator.calculator.model.operations.Operation;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ConditionOperation {

    private List<ConditionChain> conditionChains;

    private Operation operation;

}
