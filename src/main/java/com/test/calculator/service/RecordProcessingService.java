package com.test.calculator.service;

import com.test.calculator.calculator.ConditionOperationManager;
import com.test.calculator.model.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
public class RecordProcessingService {

    private final RecordQueue recordsQueue;
    private final ConditionOperationManager operationManager;

    public RecordProcessingService(RecordQueue recordsQueue, ConditionOperationManager operationManager) {
        this.recordsQueue = recordsQueue;
        this.operationManager = operationManager;
    }


    public ConcurrentHashMap<String, AtomicReference<BigDecimal>>
                                processRecords(ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap) {
        try {
            while(true) {
                Record record = recordsQueue.take();
                if (record.equals(Record.builder().build())) {
                    break;
                }

                resultMap = operationManager.performCalculations(resultMap, record);
            }
        } catch (InterruptedException e) {
            log.error("Error while processing a record. Message: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        return resultMap;
    }
}
