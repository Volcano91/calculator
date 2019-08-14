package com.test.calculator.validator.file;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileExtensionValidatorTest {

    private FileExtensionValidator validator;

    @Before
    public void setUp() {
        validator = new FileExtensionValidator();
    }

    @Test
    public void should_validate_file_extension() {
        //GIVEN
        String fileLocation = "classpath:test.txt";

        //WHEN
         boolean isExtensionValid = validator.isValid(fileLocation);

        //THEN
        assertThat(isExtensionValid).isFalse();
    }
}
