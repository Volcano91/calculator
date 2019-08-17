package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.operations.MaxOperation;
import com.test.calculator.calculator.model.operations.MeanOperation;
import com.test.calculator.calculator.model.operations.Operation;
import com.test.calculator.calculator.model.operations.SumOperation;
import org.springframework.stereotype.Component;

@Component
public class OperationProvider {

    public Operation getMaxOperation(String column) {
        return MaxOperation.builder()
                .column(column)
                .build();
    }

    public Operation getMeanOperation(String column) {
        return MeanOperation.builder()
                    .column(column)
                    .build();
    }

    public Operation getSumOperation(String column) {
        return SumOperation.builder()
                    .column(column)
                    .build();
    }

}
