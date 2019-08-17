package com.test.calculator.calculator.model.operations;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


@Value
@Builder(toBuilder = true)
public class SumOperation implements Operation {

    private String column;

    private String operationKey;

    @Override
    public void execute(ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap) {
        AtomicReference<BigDecimal> result = resultMap.get(operationKey);
        BigDecimal columnDecimal = NumberUtils.createBigDecimal(column);

        result.getAndAccumulate(columnDecimal, BigDecimal::add);
    }

}

