package com.apothuaud.automation.bdd.steps;

import com.apothuaud.automation.bdd.steps.ScenarioContext;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebSettings {

    @Given("I want to use {string} driver")
    public void iWantToUseDriver(String driverType) {

        DriverManagerType driverManagerType = DriverManagerType.valueOf(driverType.toUpperCase());

        switch (driverManagerType){

            case CHROME:
                WebDriverManager.chromedriver().setup();
                ScenarioContext.getInstance().setWebDriver(new ChromeDriver());
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                ScenarioContext.getInstance().setWebDriver(new FirefoxDriver());
                break;

            case OPERA:
                WebDriverManager.operadriver().setup();
                ScenarioContext.getInstance().setWebDriver(new OperaDriver());
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                ScenarioContext.getInstance().setWebDriver(new EdgeDriver());
                break;

            case IEXPLORER:
                WebDriverManager.iedriver().setup();
                ScenarioContext.getInstance().setWebDriver(new InternetExplorerDriver());
                break;
        }
    }
}
