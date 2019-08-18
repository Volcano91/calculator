package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileExtensionValidator implements FileValidator {

    private static final String EXCEPTION_MESSAGE = "File has  an extension that differs from csv.";

    private FileValidator nextValidator;

    @Override
    public File validate(File file) {
        if(FilenameUtils.getExtension(file.getName()).endsWith("csv")) {
            if (nextValidator != null) {
                file = nextValidator.validate(file);
            }
        }
        else {
            throw new FileException(EXCEPTION_MESSAGE);
        }
        return file;
    }

    @Override
    public void setNext(FileValidator validator) {
        this.nextValidator = validator;
    }
}
