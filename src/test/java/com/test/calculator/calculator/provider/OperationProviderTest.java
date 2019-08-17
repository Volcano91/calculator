package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.operations.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.test.calculator.OperationTestModel.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OperationProviderTest {

    private OperationProvider provider;

    @Before
    public void setUp() {
        provider = new OperationProvider();
    }

    @Test
    public void should_return_max_operation() {
        //GIVEN
        String columnName = "Price";
        String column = "0.2";

        //WHEN
        Operation actual = provider.getMaxOperation(columnName, column);

        //THEN
        assertThat(actual).isEqualTo(maxOperation(columnName, column));
    }

    @Test
    public void should_return_mean_operation() {
        //GIVEN
        String columnName = "Price";
        String column = "0.4";

        //WHEN
        Operation actual = provider.getMeanOperation(columnName, column);

        //THEN
        assertThat(actual).isEqualTo(meanOperation(columnName, column));
    }

    @Test
    public void should_return_sum_operation() {
        //GIVEN
        String columnName = "Price";
        String column = "33.22";

        //WHEN
        Operation actual = provider.getSumOperation(columnName, column);

        //THEN
        assertThat(actual).isEqualTo(sumOperation(columnName, column));
    }
}