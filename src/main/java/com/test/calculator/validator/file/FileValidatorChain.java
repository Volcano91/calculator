package com.test.calculator.validator.file;

import com.test.calculator.exceptions.FileException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class FileValidatorChain {

    private List<FileValidator> validators;

    public FileValidatorChain(List<FileValidator> fileValidators) {
        this.validators = fileValidators;
    }

    public File validate(String fileName) throws FileException {

        try {
            File file = ResourceUtils.getFile(fileName);

            for (int i = 0; i < validators.size() - 1; i++) {
                validators.get(i).setNext(validators.get(i + 1));
            }

            return validators.get(0).validate(file);

        } catch (FileNotFoundException | FileException ex) {
            throw new FileException("File didn't pass a validation : " + ex.getMessage());
        }

    }
}
