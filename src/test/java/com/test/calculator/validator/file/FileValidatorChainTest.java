package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static com.test.calculator.TestModel.NULL_FILE_PATH;
import static com.test.calculator.TestModel.RESOURCE_LOCATION;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileValidatorChainTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private FileExtensionValidator extensionValidator;

    private FileConsistencyValidator consistencyValidator;

    private FileValidatorChain chain;

    @Before
    public void setUp() {
        consistencyValidator = new FileConsistencyValidator();
        consistencyValidator.setNext(extensionValidator);
        chain = new FileValidatorChain(asList(consistencyValidator, extensionValidator));
    }

    @Test
    public void should_return_a_valid_file() throws FileNotFoundException {
        //GIVEN
        File expected = ResourceUtils.getFile(RESOURCE_LOCATION);

        when(extensionValidator.validate(expected)).thenReturn(expected);

        //WHEN
        File actual = chain.validate(RESOURCE_LOCATION);

        //THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void should_throw_exception_on_failed_validation() {
        //GIVEN
        expectedException.expect(FileException.class);
        expectedException.expectMessage("File doesn't exist or null.");

        //WHEN
        chain.validate(NULL_FILE_PATH);

    }

    @Test
    public void should_throw_exception_on_wrong_extension() throws FileNotFoundException {
        //GIVEN
        File file = ResourceUtils.getFile(RESOURCE_LOCATION);

        doThrow(FileException.class).when(extensionValidator).validate(file);

        expectedException.expect(FileException.class);

        //WHEN
        chain.validate(RESOURCE_LOCATION);
    }
}