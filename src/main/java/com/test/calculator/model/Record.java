package com.test.calculator.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Record {

    private Long number;

    private LocalDate date;

    private String isin;

    private String currency;

    private String price;

    private String volume;

    private String turnOver;

}
