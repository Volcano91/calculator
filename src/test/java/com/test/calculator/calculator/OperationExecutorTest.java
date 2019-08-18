package com.test.calculator.calculator;

import com.test.calculator.model.Record;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import static com.test.calculator.OperationTestModel.sumOperation;
import static com.test.calculator.TestModel.SUM_TURN_OVER;
import static com.test.calculator.TestModel.resultMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OperationExecutorTest {

    private OperationExecutor executor;

    @Before
    public void setUp() {
        executor = new OperationExecutor();
    }

    @Test
    public void should_execute_operation() {
        //GIVEN
        String columnName = "TurnOver";
        Record record = Record.builder()
                .date("2019-07-14")
                .isin("PLAIRWY00017")
                .currency("PLN")
                .price("0.4")
                .volume("0")
                .turnOver("33.22")
                .build();

        ConcurrentHashMap<String, AtomicReference<BigDecimal>> map = resultMap();

        //WHEN
        executor.execute(map, sumOperation(columnName, record.getTurnOver()));

        //THEN
        assertThat(map.get(SUM_TURN_OVER).get()).isEqualTo(NumberUtils.createBigDecimal(record.getTurnOver()));
    }
}
