package com.test.calculator;

import com.test.calculator.calculator.model.operations.MaxOperation;
import com.test.calculator.calculator.model.operations.MeanOperation;
import com.test.calculator.calculator.model.operations.Operation;
import com.test.calculator.calculator.model.operations.SumOperation;
import org.apache.commons.lang3.StringUtils;

public final class OperationTestModel {

    private OperationTestModel() { }

    public static Operation maxOperation(String columnName, String columnValue) {
        return MaxOperation.builder()
                .operationKey(StringUtils.join("max", columnName))
                .column(columnValue)
                .build();
    }

    public static Operation meanOperation(String columnName, String column) {
        return MeanOperation.builder()
                .operationKey(StringUtils.join("mean", columnName))
                .counterKey(StringUtils.join("mean", columnName, "Counter"))
                .column(column)
                .build();
    }

    public static Operation sumOperation(String columnName, String column) {
        return SumOperation.builder()
                .operationKey(StringUtils.join("sum", columnName))
                .column(column)
                .build();
    }
}
