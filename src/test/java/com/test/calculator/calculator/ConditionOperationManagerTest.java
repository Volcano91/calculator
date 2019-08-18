package com.test.calculator.calculator;

import com.test.calculator.calculator.provider.ConditionOperationProvider;
import com.test.calculator.calculator.validator.ConditionValidator;
import com.test.calculator.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ConcurrentHashMap;

import static com.test.calculator.ConditionOperationTestModel.conditionOperations;
import static com.test.calculator.ConditionTestModel.*;
import static com.test.calculator.OperationTestModel.sumOperation;
import static com.test.calculator.TestModel.resultMap;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConditionOperationManagerTest {

    @Mock
    private ConditionOperationProvider provider;

    @Mock
    private ConditionValidator validator;

    @Mock
    private OperationExecutor executor;

    private ConditionOperationManager manager;

    @Before
    public void setUp() {
        manager = new ConditionOperationManager(provider, validator, executor);
    }

    @Test
    public void should_validate_the_record_and_do_operations() {
        //GIVEN
        Record record = Record.builder()
                .date("2019-07-14")
                .isin("PLAIRWY00017")
                .currency("PLN")
                .price("0.4")
                .volume("0")
                .turnOver("33.22")
                .build();
        String columnName = "TurnOver";
        ConcurrentHashMap expectedMap = resultMap();

        when(provider.getConditionOperations(record)).thenReturn(conditionOperations(record));
        when(validator.validate(firstConditionChain(), record)).thenReturn(false);
        when(validator.validate(secondConditionChain(), record)).thenReturn(false);
        when(validator.validate(thirdConditionChain(), record)).thenReturn(false);
        when(validator.validate(fourthConditionChain(), record)).thenReturn(false);
        when(validator.validate(fifthConditionChain(), record)).thenReturn(true);

        //WHEN
        ConcurrentHashMap actual = manager.performCalculations(expectedMap, record);

        //THEN
        verify(executor, times(1))
                .execute(expectedMap, sumOperation(columnName, record.getTurnOver()));
    }
}