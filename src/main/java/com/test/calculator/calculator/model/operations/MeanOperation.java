package com.test.calculator.calculator.model.operations;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Value
@Builder(toBuilder = true)
public class MeanOperation implements Operation {

    private String column;

    private String operationKey;

    private String counterKey;

    @Override
    public void execute(ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap) {
        AtomicReference<BigDecimal> result = resultMap.get(operationKey);
        AtomicReference<BigDecimal> counter = resultMap.get(counterKey);

        BigDecimal columnDecimal = NumberUtils.createBigDecimal(column);

        result.getAndAccumulate(columnDecimal, BigDecimal::add);
        counter.getAndSet(counter.get().add(BigDecimal.ONE));
    }
}
