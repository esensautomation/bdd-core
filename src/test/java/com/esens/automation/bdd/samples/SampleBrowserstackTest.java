package com.esens.automation.bdd.samples;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleBrowserstackTest {

    public static String accessKey = "Az5eRwh84uLztRBKCws6";
    public static String userName = "adrianpothuaud1";

    public static void main(String args[]) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("device", "Samsung Galaxy S8");
        caps.setCapability("os_version", "7.0");
        caps.setCapability("app", "bs://fc6cd3f15300059397f07087333ab41d7244f282");


        AndroidDriver driver = new AndroidDriver(new URL("http://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);

        /* Write your Custom code here */

        driver.quit();
    }
}
