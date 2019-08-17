package com.test.calculator;

import com.test.calculator.calculator.model.conditions.condition.Condition;
import com.test.calculator.calculator.model.conditions.condition.DateCondition;
import com.test.calculator.calculator.model.conditions.condition.ISINCondition;
import com.test.calculator.calculator.model.conditions.condition_chain.AndConditionChain;
import com.test.calculator.calculator.model.conditions.condition_chain.ConditionChain;
import com.test.calculator.calculator.model.conditions.condition_chain.OrConditionChain;

import java.util.List;

import static java.util.Arrays.asList;

public final class ConditionTestModel {

    private ConditionTestModel() { }

    public static List<ConditionChain> firstConditionChain() {
        return asList(AndConditionChain.builder()
                .conditions(firstConditions())
                .build());
    }

    public static List<ConditionChain> secondConditionChain() {
        return asList(AndConditionChain.builder()
                .conditions(secondConditions())
                .build());
    }

    public static List<ConditionChain> thirdConditionChain() {
        return asList(AndConditionChain.builder()
                .conditions(thirdConditions())
                .build());
    }

    public static List<ConditionChain> fourthConditionChain() {
        return asList(AndConditionChain.builder()
                .conditions(fourthConditions())
                .build());
    }

    public static List<ConditionChain> fifthConditionChain() {
        return asList(
                AndConditionChain.builder()
                        .conditions(fifthAndConditions())
                        .build(),
                OrConditionChain.builder()
                        .conditions(fifthOrConditions())
                        .build());
    }

    public static List<Condition> firstConditions() {
        return asList(createDateCondition("2019-07-29"));
    }

    public static List<Condition> secondConditions() {
        return asList(createDateCondition("2019-07"), createIsinCondition("PLACTIN00018"));
    }

    public static List<Condition> thirdConditions() {
        return asList(createDateCondition("2019-07"), createIsinCondition("PLMCINT00013"));
    }

    public static List<Condition> fourthConditions() {
        return asList(createIsinCondition("PLAIRWY00017"));
    }

    public static List<Condition> fifthAndConditions() {
        return asList(createIsinCondition("PLAIRWY00017"));
    }

    public static List<Condition> fifthOrConditions() {
        return asList(createDateCondition("2019-08-08"), createDateCondition("2019-08-09"));
    }

    private static DateCondition createDateCondition(String columnValue) {
        return DateCondition.builder()
                .dateCondition(columnValue)
                .build();
    }

    private static ISINCondition createIsinCondition(String columnValue) {

        return ISINCondition.builder()
                .condition(columnValue)
                .build();
    }
}
