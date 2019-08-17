package com.test.calculator.service;

import com.test.calculator.calculator.model.CalculationResult;
import com.test.calculator.exceptions.FileException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.test.calculator.TestModel.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuotationServiceIT {

    @Autowired
    private QuotationService service;

    @Rule
    public ExpectedException expectException = ExpectedException.none();

    @Test
    public void should_execute_read_and_processing_tasks() {

        //WHEN
        CalculationResult calculationResult = service.doCalculations(REAL_FILE_PATH);
        System.out.println(calculationResult);
        //THEN
        assertThat(calculationResult).isNotNull();
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