package com.test.calculator.tasks;

import com.test.calculator.calculator.ConditionOperationManager;
import com.test.calculator.model.Record;
import com.test.calculator.service.RecordQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecordProcessingTask implements Runnable {

    private final RecordQueue recordsQueue;
    private final ConditionOperationManager operationManager;


    public RecordProcessingTask(RecordQueue recordsQueue, ConditionOperationManager operationManager) {
        this.recordsQueue = recordsQueue;
        this.operationManager = operationManager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Record record = recordsQueue.take();
                if (record.equals(Record.builder().build())) {
                    return;
                }

                operationManager.performCalculations(record);
            }
        } catch (InterruptedException e) {
            log.error("Error while processing a record. Message: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
