package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.ConditionOperation;
import com.test.calculator.model.Record;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class ConditionOperationProvider {

    private static final String TURN_OVER = "TurnOver";
    private static final String PRICE = "Price";
    private static final String ISIN_ONE = "PLACTIN00018";
    private static final String ISIN_TWO = "PLMCINT00013";

    private final ConditionChainProvider conditionChainProvider;

    private final OperationProvider operationProvider;

    public ConditionOperationProvider(ConditionChainProvider conditionChainProvider,
                                      OperationProvider operationProvider) {
        this.conditionChainProvider = conditionChainProvider;
        this.operationProvider = operationProvider;
    }

    public List<ConditionOperation> getConditionOperations(Record record) {
        return asList(firstConditionOperation(record), secondConditionOperation(record),
                thirdConditionOperation(record), fourthConditionOperation(record),
                fifthConditionOperation((record)));
    }

    private ConditionOperation firstConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(conditionChainProvider.getFirstConditionChain())
                .operation(operationProvider.getMaxOperation(TURN_OVER,record.getTurnOver()))
                .build();
    }

    private ConditionOperation secondConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(conditionChainProvider.getSecondConditionChain())
                .operation(operationProvider.getMeanOperation(
                        StringUtils.join(PRICE, ISIN_ONE), record.getPrice()))
                .build();
    }

    private ConditionOperation thirdConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(conditionChainProvider.getThirdConditionChain())
                .operation(operationProvider.getMeanOperation(StringUtils.join(PRICE, ISIN_TWO), record.getPrice()))
                .build();
    }

    private ConditionOperation fourthConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(conditionChainProvider.getFourthConditionChain())
                .operation(operationProvider.getMaxOperation(PRICE, record.getPrice()))
                .build();
    }

    private ConditionOperation fifthConditionOperation(Record record) {
        return ConditionOperation.builder()
                .conditionChains(conditionChainProvider.getFifthConditionChain())
                .operation(operationProvider.getSumOperation(TURN_OVER, record.getTurnOver()))
                .build();
    }

}
