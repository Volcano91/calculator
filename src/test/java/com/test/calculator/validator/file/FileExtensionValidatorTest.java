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
import static com.test.calculator.TestModel.WRONG_EXTENSION_RESOURCE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileExtensionValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FileExtensionValidator validator;

    @Before
    public void setUp() {
        validator = new FileExtensionValidator();
    }

    @Test
    public void should_validate_file_extension() throws FileNotFoundException {
        //GIVEN
        File expected = ResourceUtils.getFile(RESOURCE_LOCATION);

        //WHEN
         File actual = validator.validate(expected);

        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_throw_exception_on_wrong_extension() throws FileNotFoundException {
        //GIVEN
        expectedException.expect(FileException.class);
        expectedException.expectMessage("File has  an extension that differs from csv.");
        File file = ResourceUtils.getFile(WRONG_EXTENSION_RESOURCE);

        //WHEN
        validator.validate(file);
    }
}
