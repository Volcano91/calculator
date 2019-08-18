package com.test.calculator.calculator.transformer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CalculationResultTransformer {

    private static final String SEPARATOR = " : ";
    private static final String COUNTER = "Counter";
    private static final String MEAN = "mean";

    public List<String> transform(ConcurrentHashMap<String, AtomicReference<BigDecimal>> results) {

        return results.entrySet().stream()
                .filter(entry -> !entry.getKey().contains(COUNTER))
                .map(entry -> updateResult(results, entry))
                .collect(Collectors.toList());
    }

    private String updateResult(ConcurrentHashMap<String, AtomicReference<BigDecimal>> results,
                              Map.Entry<String, AtomicReference<BigDecimal>> entry) {
        String key = entry.getKey();
        BigDecimal value = entry.getValue().get();

        return key.contains(MEAN)
                ? getMeanResult(results, key, value)
                : StringUtils.join(key, SEPARATOR, value);
    }

    private String getMeanResult(ConcurrentHashMap<String, AtomicReference<BigDecimal>> results,
                                            String key, BigDecimal value) {
        BigDecimal counter = results.get(key + COUNTER).get();

        return calculateMeanResult(key, value, counter);
    }

    private String calculateMeanResult(String key, BigDecimal value, BigDecimal counter) {
        return counter.compareTo(BigDecimal.ZERO) == NumberUtils.INTEGER_ZERO
                ? StringUtils.join(key, SEPARATOR, BigDecimal.ZERO)
                : StringUtils.join(key, SEPARATOR, value.divide(counter, MathContext.DECIMAL32));
    }
}
