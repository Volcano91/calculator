package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.operations.MaxOperation;
import com.test.calculator.calculator.model.operations.MeanOperation;
import com.test.calculator.calculator.model.operations.Operation;
import com.test.calculator.calculator.model.operations.SumOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class OperationProvider {

    public Operation getMaxOperation(String columnName, String columnValue) {
        return MaxOperation.builder()
                .operationKey(StringUtils.join("max", columnName))
                .column(columnValue)
                .build();
    }

    public Operation getMeanOperation(String columnName, String column) {
        return MeanOperation.builder()
                    .operationKey(StringUtils.join("mean", columnName))
                    .counterKey(StringUtils.join("meanCounter", columnName))
                    .column(column)
                    .build();
    }

    public Operation getSumOperation(String columnName, String column) {
        return SumOperation.builder()
                    .operationKey(StringUtils.join("sum", columnName))
                    .column(column)
                    .build();
    }

}
