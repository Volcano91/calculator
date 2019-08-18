package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.operations.MaxOperation;
import com.test.calculator.calculator.model.operations.MeanOperation;
import com.test.calculator.calculator.model.operations.Operation;
import com.test.calculator.calculator.model.operations.SumOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class OperationProvider {

    private static final String MAX = "max";
    private static final String MEAN = "mean";
    private static final String SUM = "sum";
    private static final String COUNTER = "Counter";

    public Operation getMaxOperation(String columnName, String columnValue) {
        return MaxOperation.builder()
                .operationKey(StringUtils.join(MAX, columnName))
                .column(columnValue)
                .build();
    }

    public Operation getMeanOperation(String columnName, String column) {
        return MeanOperation.builder()
                .operationKey(StringUtils.join(MEAN, columnName))
                .counterKey(StringUtils.join(MEAN, columnName, COUNTER))
                .column(column)
                .build();
    }

    public Operation getSumOperation(String columnName, String column) {
        return SumOperation.builder()
                .operationKey(StringUtils.join(SUM, columnName))
                .column(column)
                .build();
    }

}
