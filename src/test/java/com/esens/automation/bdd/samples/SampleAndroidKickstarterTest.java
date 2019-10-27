package com.esens.automation.bdd.samples;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SampleAndroidKickstarterTest {

    public static void main (String[] args) throws MalformedURLException {

        URL appiumHub = new URL("http://localhost:4723/wd/hub");

        File apk = new File("src/test/resources/data/samples/android/kickstarter/apk/app-external-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_2_API_29");
        capabilities.setCapability("app", apk.getAbsolutePath());

        AndroidDriver<AndroidElement> androidDriver = new AndroidDriver(appiumHub, capabilities);

        // tests

        AndroidElement burgerMenu = androidDriver.findElement(By.id("com.kickstarter.kickstarter.debug:id/menu_button"));
        burgerMenu.click();



        // end of tests

        androidDriver.quit();
    }
}
