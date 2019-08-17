package com.test.calculator.calculator.provider;

import com.test.calculator.calculator.model.conditions.condition_chain.ConditionChain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.test.calculator.ConditionTestModel.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConditionChainProviderTest {

    @Mock
    private ConditionProvider conditionProvider;

    private ConditionChainProvider chainProvider;

    @Before
    public void setUp() {
        chainProvider = new ConditionChainProvider(conditionProvider);
    }

    @Test
    public void should_return_first_condition_chain() {
        //GIVEN
        when(conditionProvider.getFirstConditions()).thenReturn(firstConditions());

        //WHEN
        List<ConditionChain> actual = chainProvider.getFirstConditionChain();

        //THEN
        assertThat(actual).isEqualTo(firstConditionChain());
    }

    @Test
    public void should_return_second_condition_chain() {
        //GIVEN
        when(conditionProvider.getSecondConditions()).thenReturn(secondConditions());

        //WHEN
        List<ConditionChain> actual = chainProvider.getSecondConditionChain();

        //THEN
        assertThat(actual).isEqualTo(secondConditionChain());
    }

    @Test
    public void should_return_third_condition_chain() {
        //GIVEN
        when(conditionProvider.getThirdConditions()).thenReturn(thirdConditions());

        //WHEN
        List<ConditionChain> actual = chainProvider.getThirdConditionChain();

        //THEN
        assertThat(actual).isEqualTo(thirdConditionChain());
    }

    @Test
    public void should_return_fourth_condition_chain() {
        //GIVEN
        when(conditionProvider.getFourthConditions()).thenReturn(fourthConditions());

        //WHEN
        List<ConditionChain> actual = chainProvider.getFourthConditionChain();

        //THEN
        assertThat(actual).isEqualTo(fourthConditionChain());
    }

    @Test
    public void should_return_fifth_condition_chain() {
        //GIVEN
        when(conditionProvider.getFifthAndConditions()).thenReturn(fifthAndConditions());
        when(conditionProvider.getFifthOrConditions()).thenReturn(fifthOrConditions());

        //WHEN
        List<ConditionChain> actual = chainProvider.getFifthConditionChain();

        //THEN
        assertThat(actual).isEqualTo(fifthConditionChain());
    }
}