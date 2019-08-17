package com.test.calculator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public final class TestModel {

    public static final String RESOURCE_LOCATION = "classpath:csv/test.csv";
    public static final String WRONG_DATE_RESOURCE = "classpath:csv/wrongDate.csv";
    public static final String SIZES_RESOURCE = "classpath:csv/sizes.csv";
    public static final String NON_NUMERIC_RESOURCE = "classpath:csv/nonNumericValue.csv";
    public static final String WRONG_EXTENSION_RESOURCE = "classpath:test.txt";
    public static final String WRONG_CURRENCY_RESOURCE = "classpath:csv/wrongCurrency.csv";
    public static final String REAL_FILE_PATH = "classpath:csv/Stocks.csv";
    public static final String EXCEPTION_MESSAGE = "File has  an extension that differs from csv.";
    public static final String WRONG_FILE_PATH = "classpath:test.txt";
    public static final String NULL_FILE_PATH = "";


    private TestModel() { }

    public static CSVParser getParserForFile(String filePath) throws IOException {
        File testFile = ResourceUtils.getFile(filePath);

        return CSVParser.parse(testFile, Charset.defaultCharset(), CSVFormat.RFC4180.withDelimiter(';'));
    }

    public static ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap() {
        ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap = new ConcurrentHashMap<>();

        resultMap.put("maxTurnOver", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanPricePLMCINT00013", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanPricePLACTIN00018", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanCounterPricePLMCINT00013", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanCounterPricePLACTIN00018", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("maxPrice", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("sumTurnOver", new AtomicReference<>(BigDecimal.ZERO));

        return resultMap;
    }

    public static ConcurrentHashMap<String, AtomicReference<BigDecimal>> updatedMap() {
        ConcurrentHashMap<String, AtomicReference<BigDecimal>> resultMap = new ConcurrentHashMap<>();

        resultMap.put("maxTurnOver", new AtomicReference<>(BigDecimal.valueOf(33.22)));
        resultMap.put("meanPricePLMCINT00013", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanPricePLACTIN00018", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanCounterPricePLMCINT00013", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("meanCounterPricePLACTIN00018", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("maxPrice", new AtomicReference<>(BigDecimal.ZERO));
        resultMap.put("sumTurnOver", new AtomicReference<>(BigDecimal.ZERO));

        return resultMap;
    }

}
