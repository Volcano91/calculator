package com.test.calculator.calculator.transformer;

import com.test.calculator.calculator.model.CalculationResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

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
        CalculationResult expected = CalculationResult.builder()
                                        .maxTurnover(BigDecimal.TEN)
                                        .meanPrice(BigDecimal.ONE)
                                        .meanPrice(BigDecimal.ONE)
                                        .maxPrice(BigDecimal.TEN)
                                        .sumTurnOver(BigDecimal.TEN)
                                        .build();
        //WHEN
        CalculationResult actual = calculationResultTransformer.transform(initializeMap());

        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    private ConcurrentHashMap initializeMap() {
        ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap = new ConcurrentHashMap<>();

        resultMap.put("maxTurnOver", new AtomicReference<>(BigDecimal.TEN));
        resultMap.put("meanPricePLMCINT00013", new AtomicReference<>(BigDecimal.ONE));
        resultMap.put("meanPricePLACTIN00018", new AtomicReference<>(BigDecimal.ONE));
        resultMap.put("meanCounterPricePLMCINT00013", new AtomicReference<>(BigDecimal.ONE));
        resultMap.put("meanCounterPricePLACTIN00018", new AtomicReference<>(BigDecimal.ONE));
        resultMap.put("maxPrice", new AtomicReference<>(BigDecimal.TEN));
        resultMap.put("sumTurnOver", new AtomicReference<>(BigDecimal.TEN));

        return resultMap;
    }
}