package com.test.calculator.validator.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

@Component
public class FileExtensionValidator implements FileValidator {

    @Override
    public boolean validate(String fileName) {
        return FilenameUtils.getExtension(fileName).endsWith(".csv");
    }
}
