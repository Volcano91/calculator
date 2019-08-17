package com.test.calculator.service;

import com.test.calculator.exceptions.FileException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.test.calculator.TestModel.*;
import static java.util.Arrays.asList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuotationServiceIT {

    @Autowired
    private QuotationService service;

    @Rule
    public ExpectedException expectException = ExpectedException.none();

    @Test
    public void should_execute_read_and_processing_tasks() {
        //GIVEN
        List<String> conditions = asList("2019-07", "PLMCINT00013");
        //WHEN
        service.doCalculations(REAL_FILE_PATH);
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