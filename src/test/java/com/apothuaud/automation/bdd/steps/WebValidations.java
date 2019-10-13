package com.apothuaud.automation.bdd.steps;

import com.apothuaud.automation.bdd.steps.ScenarioContext;
import com.apothuaud.automation.bdd.web.WebSelector;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebValidations {

    @Then("I save driver url in scenario params as {string}")
    public void iSaveDriverUrlInScenarioParamsAs(String paramName) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        String url = webDriver.getCurrentUrl();

        ScenarioContext.getInstance().getScenarioParams().put(paramName, url);
    }

    @Then("I should see element {string}")
    public void iShouldSeeElement(String selectorExpr) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selectorExpr);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        Assertions.assertThat(element.isDisplayed()).isTrue();
    }
}
