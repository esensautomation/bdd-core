package com.apothuaud.automation.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenarioParamsActions {

    @Then("I extract regexp {string} from {string} to {string}")
    public void iExtractRegexpFromTo(String regex, String firstParam, String secondParamName) {

        firstParam = ScenarioContext.getInstance().getScenarioParams().get(firstParam);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(firstParam);

        if(matcher.find()){

            String secondParam = matcher.group(1);

            ScenarioContext.getInstance().getScenarioParams().put(secondParamName, secondParam);
        }
    }
}
