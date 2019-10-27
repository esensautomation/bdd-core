package com.esens.automation.bdd.samples;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleLambdaTestTest {


    private static final String gridURL = "@hub.lambdatest.com/wd/hub";

    private static DesiredCapabilities capabilities = new DesiredCapabilities();

    private static final String username = "apothuaud";
    private static final String accesskey = "rMsKmd8gwCSXKsFjm2OzjI0hoyk63q7NLAUK1ZD0m8mrOmHWyA";

    private static RemoteWebDriver driver = null;

    private static boolean status = false;

    public static void main (String[] args) {

        setUpCaps();
        setUpDriver();
        runTest();

        System.out.println((status)?"TEST PASSED !":"TEST FAILED !");
    }

    private static void setUpCaps() {

        capabilities.setCapability("platform", "OS X Mavericks");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version","40.0");
//
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("version", "70.0");
//        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
    }

    private static void setUpDriver() {

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL : " + gridURL);
        }
    }

    private static void runTest() {

        driver.get("https://lambdatest.github.io/sample-todo-app/");

        //Let's mark done first two items in the list.
        driver.findElement(By.name("li1")).click();
        driver.findElement(By.name("li2")).click();

        // Let's add an item in the list.
        driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
        driver.findElement(By.id("addbutton")).click();

        // Let's check that the item we added is added in the list.
        String enteredText = driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
        if (enteredText.equals("Yey, Let's add it to list")) {
            status = true;
        }
    }
}
