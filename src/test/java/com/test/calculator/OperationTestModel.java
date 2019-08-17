package com.test.calculator;

import com.test.calculator.calculator.model.operations.MaxOperation;
import com.test.calculator.calculator.model.operations.MeanOperation;
import com.test.calculator.calculator.model.operations.SumOperation;

public final class OperationTestModel {

    private OperationTestModel() { }

    public static MaxOperation maxOperation(String column) {
        return MaxOperation.builder()
                    .column(column)
                    .build();
    }

    public static MeanOperation meanOperation(String column) {
        return MeanOperation.builder()
                    .column(column)
                    .build();
    }

    public static SumOperation sumOperation(String column) {
        return SumOperation.builder()
                    .column(column)
                    .build();
    }
}
