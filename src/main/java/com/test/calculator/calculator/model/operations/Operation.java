package com.test.calculator.calculator.model.operations;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


public interface Operation {

    void execute(ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap);

}
