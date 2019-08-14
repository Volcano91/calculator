package com.test.calculator.validator.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

@Component
public class FileExtensionValidator implements FileValidator {

    @Override
    public boolean isValid(String path) {
        return FilenameUtils.getExtension(path).endsWith("csv");
    }
}
