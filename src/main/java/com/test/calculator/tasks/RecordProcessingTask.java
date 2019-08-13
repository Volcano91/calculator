package com.test.calculator.tasks;

import com.test.calculator.model.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecordProcessingTask implements Runnable {

    private final RecordQueue recordsQueue;

    public RecordProcessingTask(RecordQueue recordsQueue) {
        this.recordsQueue = recordsQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Record record = recordsQueue.take();
                if (record.equals(Record.builder().build())) {
                    return;
                }
                //TODO implement an actual processing
                System.out.println("Take number : " + record);
            }
        } catch (InterruptedException e) {
            log.error("Error while processing a record. Message: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
