package com.test.calculator.service;

import com.test.calculator.tasks.RecordQueue;
import com.test.calculator.service.tasks.CsvFileReadingTaskTester;
import com.test.calculator.service.tasks.RecordProcessingTaskTester;
import com.test.calculator.service.tasks.ThreadWrapper;
import com.test.calculator.transformer.RecordTransformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
public class QuotationServiceIT {

    private static final int COUNT = 2;
    private RecordQueue recordQueue;
    private RecordTransformer transformer;

    @Before
    public void setUp() {
        recordQueue = new RecordQueue();
        transformer = new RecordTransformer();
    }

    @Test
    public void should_execute_read_and_processing_tasks() throws InterruptedException {
        //GIVEN
        ThreadWrapper fileReaderThread = new ThreadWrapper();
        ThreadWrapper recordProcessingThread = new ThreadWrapper();

        CountDownLatch latch = new CountDownLatch(COUNT);

        CsvFileReadingTaskTester csvFileReaderTester = new CsvFileReadingTaskTester(recordQueue, transformer, latch);
        RecordProcessingTaskTester recordProcessorTester = new RecordProcessingTaskTester(recordQueue, latch);

        //WHEN
        fileReaderThread.executeTask(csvFileReaderTester);
        recordProcessingThread.executeTask(recordProcessorTester);

        latch.await();
    }
}