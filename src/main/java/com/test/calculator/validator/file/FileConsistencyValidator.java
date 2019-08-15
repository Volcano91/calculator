package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileConsistencyValidator implements FileValidator {

    private FileValidator nextValidator;

    @Override
    public File validate(File file) {
        File validatedFile = file;

        if(validatedFile != null) {
            validatedFile = validatedFile.exists() && validatedFile != null ? validatedFile : null;

            if (nextValidator != null) {
               validatedFile = nextValidator.validate(validatedFile);
            }
        }
        else {
            throw new FileException("File doesn't exist or null.");
        }
        return  validatedFile;
    }

    @Override
    public void setNext(FileValidator validator) {
        this.nextValidator = validator;
    }
}
