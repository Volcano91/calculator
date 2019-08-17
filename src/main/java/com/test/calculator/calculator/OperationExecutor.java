package com.test.calculator.calculator;

import com.test.calculator.calculator.model.operations.Operation;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class OperationExecutor {

    public void execute(ConcurrentHashMap resultMap, Operation operation) {
        operation.execute(resultMap);
    }
}
