package com.test.calculator.service;

import com.test.calculator.exceptions.FileException;
import com.test.calculator.service.tasks.RecordProcessingTaskTester;
import com.test.calculator.tasks.RecordQueue;
import com.test.calculator.transformer.RecordTransformer;
import com.test.calculator.validator.file.FileExtensionValidator;
import com.test.calculator.validator.file.FileValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotationServiceIT {

    private static final String EXCEPTION_MESSAGE = "File extension differs from csv";
    private static final String WRONG_FILE_PATH = "classpath:wrongFile.wrong";
    private static final String CORRECT_FILE_PATH = "classpath:csv/Stocks.csv";

    @Rule
    public ExpectedException expectException = ExpectedException.none();

    private final CountDownLatch latch = new CountDownLatch(1);
    private RecordQueue recordQueue;
    private RecordTransformer transformer;
    private FileValidator validator;
    private FileReaderService readerService;
    private RecordProcessorService processorService;
    private RecordProcessingTaskTester processingTaskTester;
    private QuotationService service;

    @Before
    public void setUp() {
        recordQueue = new RecordQueue();
        transformer = new RecordTransformer();
        validator = new FileExtensionValidator();
        processingTaskTester = new RecordProcessingTaskTester(recordQueue, latch);
        readerService = new FileReaderService(recordQueue, transformer, validator);
        processorService = new RecordProcessorService(recordQueue, processingTaskTester);
        service = new QuotationService(recordQueue, readerService, processorService, validator);
    }

    @Test
    public void should_execute_read_and_processing_tasks() throws InterruptedException {
        //WHEN
        service.doCalculations(CORRECT_FILE_PATH);

        latch.await();
    }

    @Test
    public void should_not_execute_on_wrong_file() {
        //GIVEN
        expectException.expect(FileException.class);
        expectException.expectMessage(EXCEPTION_MESSAGE);

        //WHEN
        service.doCalculations(WRONG_FILE_PATH);
    }
}