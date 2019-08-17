package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.conditions.condition.Condition;
import com.test.calculator.calculator.model.conditions.condition.DateCondition;
import com.test.calculator.calculator.model.conditions.condition.ISINCondition;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class ConditionProvider {

    public List<Condition> getFirstConditions() {
        return asList(createDateCondition("2019-07-29"));
    }

    public List<Condition> getSecondConditions() {
        return asList(createDateCondition("2019-07"), createIsinCondition("PLACTIN00018"));
    }

    public List<Condition> getThirdConditions() {
        return asList(createDateCondition("2019-07"), createIsinCondition("PLMCINT00013"));
    }

    public List<Condition> getFourthConditions() {
        return asList(createIsinCondition("PLAIRWY00017"));
    }

    public List<Condition> getFifthAndConditions() {
        return asList(createIsinCondition("PLAIRWY00017"));
    }

    public List<Condition> getFifthOrConditions() {
        return asList(createDateCondition("2019-08-08"), createDateCondition("2019-08-09"));
    }

    private DateCondition createDateCondition(String columnValue) {
        return DateCondition.builder()
                .dateCondition(columnValue)
                .build();
    }

    private ISINCondition createIsinCondition(String columnValue) {

        return ISINCondition.builder()
                    .condition(columnValue)
                    .build();
    }


}
