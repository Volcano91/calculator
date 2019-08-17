package com.test.calculator.calculator;

import com.test.calculator.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.test.calculator.OperationTestModel.sumOperation;

@RunWith(MockitoJUnitRunner.class)
public class OperationExecutorTest {

    private OperationExecutor executor;

    @Before
    public void setUp() {
        executor = new OperationExecutor();
    }

    @Test
    public void should_execute_operation() {
        Record record = Record.builder()
                .date("2019-07-14")
                .isin("PLAIRWY00017")
                .currency("PLN")
                .price("0.4")
                .volume("0")
                .turnOver("33.22")
                .build();

        //WHEN
        executor.execute(sumOperation(record.getTurnOver()));

        //THEN

    }
}
