package com.esens.automation.bdd.steps.web;

import com.esens.automation.bdd.steps.ScenarioContext;
import com.esens.automation.bdd.condition.WebCondition;
import com.esens.automation.bdd.selector.WebSelector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions {

    @And("I click on web element {string}")
    public void iClickOnElement(String selector) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selector);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        element.click();
    }

    @And("I fill web field {string} with text {string}")
    public void iFillFieldWith(String selector, String inputText) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selector);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        element.sendKeys(inputText);
    }

    @When("I navigate to url")
    public void iNavigateToUrl() {

        String url = ScenarioContext.getInstance().getUrlFromScenario();

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        webDriver.get(url);
    }

    @When("I navigate to url {string}")
    public void iNavigateToUrl(String newUrl) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        webDriver.get(newUrl);
    }

    @When("I open web client")
    public void iOpenWebDriver() {

        String url = ScenarioContext.getInstance().getUrlFromScenario();

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        webDriver.get(url);
    }

    @And("I wait for web condition {string}")
    public void iWaitForWebCondition(String webConditionExpr) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebCondition webCondition = new WebCondition(webConditionExpr);

        wait.until(webCondition.getExpectedCondition());
    }
}
