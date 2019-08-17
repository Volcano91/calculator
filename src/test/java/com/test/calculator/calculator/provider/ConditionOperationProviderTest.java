package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.ConditionOperation;
import com.test.calculator.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.test.calculator.ConditionOperationTestModel.conditionOperations;
import static com.test.calculator.ConditionTestModel.*;
import static com.test.calculator.OperationTestModel.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConditionOperationProviderTest {

    @Mock
    private ConditionChainProvider chainProvider;

    @Mock
    private OperationProvider operationProvider;

    private ConditionOperationProvider conditionOperationProvider;

    @Before
    public void setUp() {
        conditionOperationProvider = new ConditionOperationProvider(chainProvider, operationProvider);
    }

    @Test
    public void should_provide_condition_operations() {
        //GIVEN
        Record record = Record.builder()
                            .date("2017-07-12")
                            .isin("ANY12345")
                            .currency("PLN")
                            .price("0.4")
                            .volume("0")
                            .turnOver("33.22")
                            .build();

        when(chainProvider.getFirstConditionChain()).thenReturn(firstConditionChain());
        when(chainProvider.getSecondConditionChain()).thenReturn(secondConditionChain());
        when(chainProvider.getThirdConditionChain()).thenReturn(thirdConditionChain());
        when(chainProvider.getFourthConditionChain()).thenReturn(fourthConditionChain());
        when(chainProvider.getFifthConditionChain()).thenReturn(fifthConditionChain());

        when(operationProvider.getMaxOperation("TurnOver", record.getTurnOver()))
                .thenReturn(maxOperation("TurnOver", record.getTurnOver()));
        when(operationProvider.getMaxOperation("Price", record.getPrice()))
                .thenReturn(maxOperation("Price", record.getPrice()));
        when(operationProvider.getMeanOperation("PricePLACTIN00018", record.getPrice()))
                .thenReturn(meanOperation("Price", record.getPrice()));
        when(operationProvider.getMeanOperation("PricePLMCINT00013", record.getPrice()))
                .thenReturn(meanOperation("Price", record.getPrice()));
        when(operationProvider.getSumOperation("TurnOver", record.getTurnOver()))
                .thenReturn(sumOperation("TurnOver", record.getTurnOver()));

        //WHEN
        List<ConditionOperation> actual = conditionOperationProvider.getConditionOperations(record);

        //THEN
        assertThat(actual).isEqualTo(conditionOperations(record));
    }
}
