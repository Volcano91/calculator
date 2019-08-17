package com.test.calculator.calculator;

import com.test.calculator.calculator.model.operations.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationExecutor {

    public void execute(Operation operation) {
        operation.execute();
    }
}
