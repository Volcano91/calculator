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
        //GIVEN
        List<String> expectedResult = asList("sumTurnOver : 5.02", "maxTurnOver : 63900.48",
                "meanPricePLMCINT00013 : 0.195", "maxPrice : 0.758", "meanPricePLACTIN00018 : 3.546667");

        //WHEN
        List<String> calculationResult = service.doCalculations(REAL_FILE_PATH);

        //THEN
        assertThat(calculationResult).isEqualTo(expectedResult);
    }

    @Test
    public void should_throw_file_exception_on_wrong_file() {
        //GIVEN
        expectException.expect(FileException.class);
        expectException.expectMessage(EXCEPTION_MESSAGE);

        //WHEN
        service.doCalculations(WRONG_FILE_PATH);
    }
}