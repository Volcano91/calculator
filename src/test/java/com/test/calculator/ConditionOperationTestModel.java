package com.test.calculator;

import com.test.calculator.calculator.model.ConditionOperation;
import com.test.calculator.model.Record;

import java.util.List;

import static com.test.calculator.ConditionTestModel.*;
import static com.test.calculator.OperationTestModel.*;
import static java.util.Arrays.asList;

public final class ConditionOperationTestModel {

    private ConditionOperationTestModel() { }


    public static List<ConditionOperation> conditionOperations(Record record) {
        return asList(
                firstConditionOperation(record),
                secondConditionOperation(record),
                thirdConditionOperation(record),
                fourthConditionOperation(record),
                fifthConditionOperation(record)
        );
    }

    public static ConditionOperation firstConditionOperation(Record record) {
        return ConditionOperation.builder()
                    .conditionChains(firstConditionChain())
                    .operation(maxOperation("TurnOver",record.getTurnOver()))
                    .build();
    }

    private static ConditionOperation secondConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(secondConditionChain())
                .operation(meanOperation("Price", record.getPrice()))
                .build();
    }

    private static ConditionOperation thirdConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(thirdConditionChain())
                .operation(meanOperation("Price", record.getPrice()))
                .build();
    }

    private static ConditionOperation fourthConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(fourthConditionChain())
                .operation(maxOperation("Price", record.getPrice()))
                .build();
    }

    private static ConditionOperation fifthConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(fifthConditionChain())
                .operation(sumOperation("TurnOver", record.getTurnOver()))
                .build();
    }
}
