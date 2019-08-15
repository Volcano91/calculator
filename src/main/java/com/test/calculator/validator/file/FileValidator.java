package com.test.calculator.validator.file;

import java.io.File;

public interface FileValidator {

    File validate(File file);

    void setNext(FileValidator validator);

}
