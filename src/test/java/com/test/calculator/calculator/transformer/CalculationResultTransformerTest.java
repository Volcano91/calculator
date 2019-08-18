package com.test.calculator.calculator.transformer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.test.calculator.TestModel.expectedResult;
import static com.test.calculator.TestModel.resultMap;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CalculationResultTransformerTest {

    private CalculationResultTransformer calculationResultTransformer;

    @Before
    public void setUp() {
        calculationResultTransformer = new CalculationResultTransformer();
    }

    @Test
    public void should_transform_map_to_result() {
        //GIVEN

        //WHEN
        List<String> actual = calculationResultTransformer.transform(resultMap());

        //THEN
        assertThat(actual).containsAll(expectedResult());
    }
}