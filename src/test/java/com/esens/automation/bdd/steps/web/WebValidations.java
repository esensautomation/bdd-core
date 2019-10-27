package com.esens.automation.bdd.steps.web;

import com.esens.automation.bdd.steps.ScenarioContext;
import com.esens.automation.bdd.selector.WebSelector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebValidations {

    @Then("I save web client url in scenario param {string}")
    public void iSaveDriverUrlInScenarioParamsAs(String paramName) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        String url = webDriver.getCurrentUrl();

        ScenarioContext.getInstance().getScenarioParams().put(paramName, url);
    }

    @Then("I should see web element {string}")
    public void iShouldSeeElement(String selectorExpr) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selectorExpr);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        Assertions.assertThat(element.isDisplayed()).isTrue();
    }

    @Then("I should see web element {string} selected")
    public void iShouldSeeWebElementSelected(String selectorExpr) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selectorExpr);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        Assertions.assertThat(element.isSelected()).isTrue();
    }

    @And("I should see text {string} in web element {string}")
    public void iShouldSeeTextInElement(String expectedText, String selectorExpr) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selectorExpr);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        String actualText = element.getText();

        Assertions.assertThat(actualText).contains(expectedText);
    }
}
