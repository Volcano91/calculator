package com.test.calculator.calculator.model.operations;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SumOperation implements Operation {

    private String column;

    @Override
    public void execute() {
        //todo implement

        System.out.println("Sum operation for " + column);
    }
}
