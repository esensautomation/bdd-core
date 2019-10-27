package com.esens.automation.bdd.steps.mobile.android;

import com.esens.automation.bdd.selector.AndroidSelector;
import com.esens.automation.bdd.steps.ScenarioContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AndroidActions {

    @When("I click on android element {string}")
    public void iClickOnAndroidElement(String selector) {

        AndroidDriver<AndroidElement> androidDriver = ScenarioContext.getInstance().getAndroidDriver();

        AndroidSelector androidSelector = new AndroidSelector(selector);
        By by = androidSelector.getAppiumSelector();

        AndroidElement element = androidDriver.findElement(by);

        element.click();
    }

    /**
     * Opens AndroidDriver client
     */
    @When("I open android client")
    public void iOpenAndroidClient() {

        URL hubUrl = ScenarioContext.getInstance().getAppiumHubUrl();

        DesiredCapabilities capabilities = ScenarioContext.getInstance().getAppiumCapabilities();

        AndroidDriver<AndroidElement> androidDriver = new AndroidDriver<AndroidElement>(hubUrl, capabilities);

        ScenarioContext.getInstance().setAndroidDriver(androidDriver);
    }
}
