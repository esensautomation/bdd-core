package com.apothuaud.automation.bdd.steps;

import com.apothuaud.automation.bdd.steps.ScenarioContext;
import com.apothuaud.automation.bdd.web.WebCondition;
import com.apothuaud.automation.bdd.web.WebSelector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions {

    @When("I open web driver")
    public void iOpenWebDriver() {

        String url = ScenarioContext.getInstance().getUrlFromScenario();

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        webDriver.get(url);
    }

    @And("I fill field {string} with {string}")
    public void iFillFieldWith(String selector, String inputText) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selector);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        element.sendKeys(inputText);
    }

    @And("I click on element {string}")
    public void iClickOnElement(String selector) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebSelector webSelector = new WebSelector(selector);
        By by = webSelector.getSeleniumSelector();

        WebElement element = webDriver.findElement(by);

        element.click();
    }

    @And("I wait for web condition {string}")
    public void iWaitForWebCondition(String webConditionExpr) {

        WebDriver webDriver = ScenarioContext.getInstance().getWebDriver();

        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        WebCondition webCondition = new WebCondition(webConditionExpr);

        wait.until(webCondition.getExpectedCondition());
    }
}
