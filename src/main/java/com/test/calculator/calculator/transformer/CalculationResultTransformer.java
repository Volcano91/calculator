package com.test.calculator.calculator.transformer;

import com.test.calculator.calculator.model.CalculationResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class CalculationResultTransformer {

    public CalculationResult transform(ConcurrentHashMap<String, AtomicReference<BigDecimal>> results) {
        return CalculationResult.builder()
                .maxTurnover(results.get("maxTurnOver").get())
                .meanPrice(getMeanPrice(results, "meanPricePLMCINT00013", "meanCounterPricePLMCINT00013"))
                .meanPrice(getMeanPrice(results, "meanPricePLACTIN00018", "meanCounterPricePLACTIN00018"))
                .maxPrice(results.get("maxPrice").get())
                .sumTurnOver(results.get("sumTurnOver").get())
                .build();

    }

    private BigDecimal getMeanPrice(ConcurrentHashMap<String, AtomicReference<BigDecimal>> results,
                                          String valueKey, String counterKey) {
        BigDecimal sum = results.get(valueKey).get();
        BigDecimal divisor = results.get(counterKey).get();

        return sum.divide(divisor, MathContext.DECIMAL32);
    }

}
