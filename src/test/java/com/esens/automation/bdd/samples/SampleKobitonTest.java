package com.esens.automation.bdd.samples;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleKobitonTest {

    public static void main(String[] args) throws MalformedURLException {

        String kobitonServerUrl = "https://apothuaud-esens:89973be0-bc7c-40f2-903d-22010676a852@api.kobiton.com/wd/hub";
        URL kobitonHub = new URL(kobitonServerUrl);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        // The generated session will be visible to you only.
        capabilities.setCapability("sessionName", "Automation test session");
        capabilities.setCapability("sessionDescription", "Automation tests");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("captureScreenshots", true);
        // The maximum size of application is 500MB
        // By default, HTTP requests from testing library are expired
        // in 2 minutes while the app copying and installation may
        // take up-to 30 minutes. Therefore, you need to extend the HTTP
        // request timeout duration in your testing library so that
        // it doesn't interrupt while the device is being initialized.
        capabilities.setCapability("app", "kobiton-store:41830");

        capabilities.setCapability("deviceGroup", "KOBITON");
        // For deviceName, platformVersion Kobiton supports wildcard
        // character *, with 3 formats: *text, text* and *text*
        // If there is no *, Kobiton will match the exact text provided
        capabilities.setCapability("deviceName", "LG G4");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("platformName", "Android");

        AndroidDriver androidDriver = new AndroidDriver(kobitonHub, capabilities);


    }
}
