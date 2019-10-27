package com.esens.automation.bdd.steps.mobile;

import com.esens.automation.bdd.steps.ScenarioContext;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileSettings {

    /**
     * Setup "app" in DesiredCapabilities for Appium Driver
     * @param appPath Path of the application to be used
     */
    @Given("I want to use appium app {string}")
    public void iWantToUseAppiumApp(String appPath) {

        if(!appPath.startsWith("src/test/resources/data/")){

            appPath = "src/test/resources/data/" + appPath;
        }

        File appFile = new File(appPath);

        ScenarioContext.getInstance().getAppiumCapabilities().setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
    }

    /**
     * Setup "deviceName" in DesiredCapabilities for Appium Driver
     * @param deviceName the device name to be used
     */
    @Given("I want to use appium device name {string}")
    public void iWantToUseAppiumDeviceName(String deviceName) {

        ScenarioContext.getInstance().getAppiumCapabilities().setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
    }

    /**
     * Setup Appium hub url
     * @param hubUrl url of appium hub to be used
     */
    @Given("I want to use appium hub url {string}")
    public void iWantToUseAppiumHubUrl(String hubUrl) throws MalformedURLException {

        URL url = new URL(hubUrl);

        ScenarioContext.getInstance().setAppiumHubUrl(url);
    }

    /**
     * Setup "platformName" in DesiredCapabilities for Appium Driver
     * @param platformName name of the platform to be used
     */
    @Given("I want to use appium platform name {string}")
    public void iWantToUseAppiumPlatformName(String platformName) {

        ScenarioContext.getInstance().getAppiumCapabilities().setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
    }
}
