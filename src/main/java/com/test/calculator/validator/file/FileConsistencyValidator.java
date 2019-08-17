package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileConsistencyValidator implements FileValidator {

    private FileValidator nextValidator;

    @Override
    public File validate(File file) {
        try {
            if (file.exists() && file != null) {
                if (nextValidator != null) {
                    file = nextValidator.validate(file);
                }
            } else {
                throw new FileException("File doesn't exist or null.");
            }
        }
        catch (NullPointerException ex) {
            throw new FileException("File doesn't exist or null.");
        }

        return  file;
    }

    @Override
    public void setNext(FileValidator validator) {
        this.nextValidator = validator;
    }
}
