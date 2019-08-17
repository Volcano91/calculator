package com.test.calculator.calculator.validator;

import com.test.calculator.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.test.calculator.ConditionTestModel.fifthConditionChain;
import static com.test.calculator.ConditionTestModel.firstConditionChain;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ConditionValidatorTest {

    private ConditionValidator validator;

    @Before
    public void setUp() {
        validator = new ConditionValidator();
    }

    @Test
    public void should_return_true_on_conditions() {
        //GIVEN
        Record record = Record.builder()
                .date("2019-07-29")
                .isin("PLACTIN00018")
                .currency("PLN")
                .price("0.4")
                .volume("0")
                .turnOver("33.22")
                .build();

        //WHEN
        boolean result = validator.validate(firstConditionChain(), record);

        //THEN
        assertThat(result).isTrue();
    }

    @Test
    public void should_return_false_on_conditions() {
        //GIVEN
        Record record = Record.builder()
                .date("2019-07-14")
                .isin("PLAIRWY00017")
                .currency("PLN")
                .price("0.4")
                .volume("0")
                .turnOver("33.22")
                .build();

        //WHEN
        boolean result = validator.validate(fifthConditionChain(), record);

        //THEN
        assertThat(result).isFalse();

    }
}