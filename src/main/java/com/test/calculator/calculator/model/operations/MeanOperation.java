package com.test.calculator.calculator.model.operations;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MeanOperation implements Operation {

    private String column;

    @Override
    public void execute() {

       //todo implement

        System.out.println("Mean operation for " + column);
    }
}
