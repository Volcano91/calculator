package com.test.calculator.service.tasks;

import com.test.calculator.tasks.RecordProcessingTask;
import com.test.calculator.tasks.RecordQueue;

import java.util.concurrent.CountDownLatch;

public class RecordProcessingTaskTester extends RecordProcessingTask {

    private final CountDownLatch latch;

    public RecordProcessingTaskTester(RecordQueue recordsQueue, CountDownLatch latch) {
        super(recordsQueue);
        this.latch = latch;
    }

    @Override
    public void run() {
        super.run();
        latch.countDown();
    }
}
