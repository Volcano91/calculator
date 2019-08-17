package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.conditions.condition.*;
import com.test.calculator.calculator.model.conditions.condition_chain.AndConditionChain;
import com.test.calculator.calculator.model.conditions.condition_chain.ConditionChain;
import com.test.calculator.calculator.model.conditions.condition_chain.OrConditionChain;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class ConditionChainProvider {

    private final ConditionProvider conditionProvider;

    public ConditionChainProvider(ConditionProvider conditionProvider) {
        this.conditionProvider = conditionProvider;
    }

    public List<ConditionChain> getFirstConditionChain() {
        return asList(getAndConditionChain(conditionProvider.getFirstConditions()));
    }

    public List<ConditionChain> getSecondConditionChain() {
        return asList(getAndConditionChain(conditionProvider.getSecondConditions()));
    }

    public List<ConditionChain> getThirdConditionChain() {
        return asList(getAndConditionChain(conditionProvider.getThirdConditions()));
    }

    public List<ConditionChain> getFourthConditionChain() {
        return asList(getAndConditionChain(conditionProvider.getFourthConditions()));
    }

    public List<ConditionChain> getFifthConditionChain() {
        return asList(getAndConditionChain(conditionProvider.getFifthAndConditions()),
                      getOrConditionChain(conditionProvider.getFifthOrConditions()));
    }

    private ConditionChain getAndConditionChain(List<Condition> conditions) {
        return AndConditionChain.builder()
                    .conditions(conditions)
                    .build();
    }

    private ConditionChain getOrConditionChain(List<Condition> conditions) {
        return OrConditionChain.builder()
                    .conditions(conditions)
                    .build();
    }
}
