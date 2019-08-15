package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static com.test.calculator.TestModel.RESOURCE_LOCATION;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileConsistencyValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FileConsistencyValidator consistencyValidator;

    @Before
    public void setUp() {
        consistencyValidator = new FileConsistencyValidator();
    }

    @Test
    public void should_return_true_if_file_exists() throws FileNotFoundException {
        //GIVEN
        File expected = ResourceUtils.getFile(RESOURCE_LOCATION);

        //WHEN
        File actual = consistencyValidator.validate(expected);

        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_throw_file_exception_on_no_file() {
        //GIVEN
        expectedException.expect(FileException.class);
        expectedException.expectMessage("File doesn't exist or null.");

        //WHEN
        consistencyValidator.validate(null);

    }
}