package com.test.calculator.service;

import com.test.calculator.calculator.model.CalculationResult;
import com.test.calculator.calculator.transformer.CalculationResultTransformer;
import com.test.calculator.service.executors.FileReadingExecutor;
import com.test.calculator.service.executors.RecordProcessingExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class QuotationService {

    private final RecordQueue recordsQueue;

    private final FileReadingExecutor fileReadingExecutor;

    private final RecordProcessingExecutor recordProcessingExecutor;

    private final CalculationResultTransformer transformer;

    private static ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap = new ConcurrentHashMap<>();

    public QuotationService(RecordQueue recordsQueue,
                            FileReadingExecutor fileReadingExecutor,
                            RecordProcessingExecutor recordProcessingExecutor,
                            CalculationResultTransformer transformer) {
        this.recordsQueue = recordsQueue;
        this.fileReadingExecutor = fileReadingExecutor;
        this.recordProcessingExecutor = recordProcessingExecutor;
        this.transformer = transformer;

        resultMap.put("maxTurnOver", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanPricePLMCINT00013", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanPricePLACTIN00018", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanCounterPricePLMCINT00013", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanCounterPricePLACTIN00018", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("maxPrice", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("sumTurnOver", new AtomicReference<>(BigDecimal.ZERO));
    }

    public CalculationResult doCalculations(String fileName) {

        fileReadingExecutor.readQuotationsFile(fileName);
        ConcurrentHashMap results = recordProcessingExecutor.processQuotations(resultMap);

        return transformer.transform(results);
    }
}
