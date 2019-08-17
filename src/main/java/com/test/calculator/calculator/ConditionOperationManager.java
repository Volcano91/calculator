package com.test.calculator.calculator;

import com.test.calculator.calculator.model.ConditionOperation;
import com.test.calculator.calculator.model.conditions.condition_chain.ConditionChain;
import com.test.calculator.calculator.model.operations.Operation;
import com.test.calculator.calculator.provider.ConditionOperationProvider;
import com.test.calculator.calculator.validator.ConditionValidator;
import com.test.calculator.model.Record;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class ConditionOperationManager {

    private final ConditionOperationProvider conditionOperationProvider;

    private final ConditionValidator conditionValidator;

    private final OperationExecutor operationExecutor;

    public ConditionOperationManager(ConditionOperationProvider conditionOperationProvider,
                                     ConditionValidator conditionValidator,
                                     OperationExecutor operationExecutor) {
        this.conditionOperationProvider = conditionOperationProvider;
        this.conditionValidator = conditionValidator;
        this.operationExecutor = operationExecutor;
    }

    public ConcurrentHashMap<String, AtomicReference<BigDecimal>> performCalculations(ConcurrentHashMap resultMap, Record record) {
        List<ConditionOperation> conditionOperations = conditionOperationProvider.getConditionOperations(record);

        for(ConditionOperation conditionOperation : conditionOperations) {
            List<ConditionChain> conditionChains = conditionOperation.getConditionChains();
            Operation operation = conditionOperation.getOperation();

            if(conditionValidator.validate(conditionChains, record)) {
                operationExecutor.execute(resultMap, operation);
            }
        }

        return resultMap;
    }
}
