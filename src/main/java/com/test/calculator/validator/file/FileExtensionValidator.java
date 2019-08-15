package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileExtensionValidator implements FileValidator {

    private FileValidator nextValidator;

    @Override
    public File validate(File file) {
        File validatedFile = file;

        if(FilenameUtils.getExtension(file.getName()).endsWith("csv")) {

            if (nextValidator != null) {
                validatedFile = nextValidator.validate(validatedFile);
            }
        }
        else {
            throw new FileException("File has  an extension that differs from csv.");
        }
        return  validatedFile;
    }

    @Override
    public void setNext(FileValidator validator) {
        this.nextValidator = validator;
    }
}
