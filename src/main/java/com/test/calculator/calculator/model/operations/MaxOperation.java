package com.test.calculator.calculator.model.operations;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MaxOperation implements Operation {

    private String column;

    @Override
    public void execute() {
//        if (rowColumn.compareTo(result.get()) == 1) {
//            result.getAndSet(rowColumn);
//        }

        System.out.println("Max operation for " + column);
    }

}
