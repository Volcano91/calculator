package com.test.calculator.service.tasks;

import com.test.calculator.tasks.CsvFileReadingTask;
import com.test.calculator.tasks.RecordQueue;
import com.test.calculator.transformer.RecordTransformer;

import java.util.concurrent.CountDownLatch;

public class CsvFileReadingTaskTester extends CsvFileReadingTask {

    private final CountDownLatch latch;

    public CsvFileReadingTaskTester(RecordQueue recordQueue,
                                    RecordTransformer recordTransformer,
                                    CountDownLatch latch) {
        super(recordQueue, recordTransformer);
        this.latch = latch;
    }

    @Override
    public void run() {
        super.run();
        latch.countDown();
    }

}
