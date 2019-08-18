package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileConsistencyValidator implements FileValidator {

    private static final String EXCEPTION_MESSAGE = "File doesn't exist or null.";

    private FileValidator nextValidator;

    @Override
    public File validate(File file) {
        try {
            if (file.exists() && file != null) {
                if (nextValidator != null) {
                   file = nextValidator.validate(file);
                }
            } else {
                throw new FileException(EXCEPTION_MESSAGE);
            }
        }
        catch (NullPointerException ex) {
            throw new FileException(EXCEPTION_MESSAGE);
        }
        return file;
    }

    @Override
    public void setNext(FileValidator validator) {
        this.nextValidator = validator;
    }
}
