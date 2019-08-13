package com.test.calculator.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Record {

    private String date;

    private String isin;

    private String currency;

    private String price;

    private String volume;

    private String turnOver;

}
