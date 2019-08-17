package com.test.calculator.calculator.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class CalculationResult {

    private BigDecimal maxTurnover;

    @Singular
    private List<BigDecimal> meanPrices;

    private BigDecimal maxPrice;

    private BigDecimal sumTurnOver;

}
