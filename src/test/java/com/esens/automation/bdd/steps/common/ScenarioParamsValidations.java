package com.esens.automation.bdd.steps.common;

import com.esens.automation.bdd.steps.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;

public class ScenarioParamsValidations {

    @Then("I should have scenario param {string} greater than scenario param {string}")
    public void iShouldHaveScenarioParamGreaterThanScenarioParam(String param1, String param2) {

        String paramValue1 = ScenarioContext.getInstance().getScenarioParams().get(param1);
        String paramValue2 = ScenarioContext.getInstance().getScenarioParams().get(param2);

        int expectedGreater = Integer.parseInt(paramValue1);
        int expectedSmaller = Integer.parseInt(paramValue2);

        Assertions.assertThat(expectedGreater).isGreaterThan(expectedSmaller);
    }

    @And("I should have scenario param {string} equals to scenario param {string}")
    public void iShouldHaveScenarioParamEqualsToScenarioParam(String param1, String param2) {

        String paramValue1 = ScenarioContext.getInstance().getScenarioParams().get(param1);
        String paramValue2 = ScenarioContext.getInstance().getScenarioParams().get(param2);

        int value1 = Integer.parseInt(paramValue1);
        int value2 = Integer.parseInt(paramValue2);

        Assertions.assertThat(value1).isEqualTo(value2);
    }
}
