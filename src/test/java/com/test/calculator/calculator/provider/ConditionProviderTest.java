package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.conditions.condition.Condition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.test.calculator.ConditionTestModel.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ConditionProviderTest {

    private ConditionProvider conditionProvider;

    @Before
    public void setUp() {
        conditionProvider = new ConditionProvider();
    }

    @Test
    public void should_provide_first_conditions() {
        //WHEN
        List<Condition> actual = conditionProvider.getFirstConditions();

        //THEN
        assertThat(actual).isEqualTo(firstConditions());
    }

    @Test
    public void should_provide_second_conditions() {
        //WHEN
        List<Condition> actual = conditionProvider.getSecondConditions();

        //THEN
        assertThat(actual).isEqualTo(secondConditions());
    }

    @Test
    public void should_provide_third_conditions() {
        //WHEN
        List<Condition> actual = conditionProvider.getThirdConditions();

        //THEN
        assertThat(actual).isEqualTo(thirdConditions());
    }

    @Test
    public void should_provide_fourth_conditions() {
        //WHEN
        List<Condition> actual = conditionProvider.getFourthConditions();

        //THEN
        assertThat(actual).isEqualTo(fourthConditions());
    }

    @Test
    public void should_provide_fifth_and_conditions() {
        //WHEN
        List<Condition> actual = conditionProvider.getFifthAndConditions();

        //THEN
        assertThat(actual).isEqualTo(fifthAndConditions());
    }

    @Test
    public void should_provide_fifth_or_conditions() {
        //WHEN
        List<Condition> actual = conditionProvider.getFifthOrConditions();

        //THEN
        assertThat(actual).isEqualTo(fifthOrConditions());
    }

}