package com.test.calculator.validator.file;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileExtentionValidatorTest {

    private FileExtensionValidator validator;

    @Before
    public void setUp() {
        validator = new FileExtensionValidator();
    }

    @Test
    public void should_validate_file_extension() throws FileNotFoundException {
        //GIVEN
        String fileLocation = "classpath:test.txt";

        //WHEN
        boolean hasCorrectExtension = validator.validate(fileLocation);

        //THEN
        assertThat(hasCorrectExtension).isFalse();
    }
}
