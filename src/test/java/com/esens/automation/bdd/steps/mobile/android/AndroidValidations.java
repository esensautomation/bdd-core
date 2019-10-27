package com.esens.automation.bdd.steps.mobile.android;

import com.esens.automation.bdd.selector.AndroidSelector;
import com.esens.automation.bdd.steps.ScenarioContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

public class AndroidValidations {

    @Then("I should see android element {string}")
    public void iShouldSeeAndroidElement(String selectorExpr) {

        AndroidDriver<AndroidElement> androidDriver = ScenarioContext.getInstance().getAndroidDriver();

        AndroidSelector androidSelector = new AndroidSelector(selectorExpr);
        By by = androidSelector.getAppiumSelector();

        AndroidElement element = androidDriver.findElement(by);

        Assertions.assertThat(element.isDisplayed()).isTrue();
    }

    @And("I should see text {string} in android element {string}")
    public void iShouldSeeTextInAndroidElement(String expectedText, String selectorExpr) {

        AndroidDriver<AndroidElement> androidDriver = ScenarioContext.getInstance().getAndroidDriver();

        AndroidSelector androidSelector = new AndroidSelector(selectorExpr);
        By by = androidSelector.getAppiumSelector();

        AndroidElement element = androidDriver.findElement(by);

        String actualText = element.getText();

        Assertions.assertThat(actualText).contains(expectedText);
    }
}
