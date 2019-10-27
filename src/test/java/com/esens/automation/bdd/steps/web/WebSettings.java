package com.esens.automation.bdd.steps.web;

import com.esens.automation.bdd.steps.ScenarioContext;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebSettings {

    /**
     * Setup web driver in Scenario Context
     *
     * @param driverType type of web client
     * @throws MalformedURLException in case hub url is bad
     */
    @Given("I want to use {string} web driver")
    public void iWantToUseDriver(String driverType) throws MalformedURLException {

        DriverManagerType driverManagerType = DriverManagerType.valueOf(driverType.toUpperCase());

        String seleniumRemoteUrl = System.getProperty("SELENIUM_REMOTE_URL");

        System.out.println("------------------------------------");
        System.out.println("USING SELENIUM GRID IS SET TO " + seleniumRemoteUrl);
        System.out.println("------------------------------------");

        if(seleniumRemoteUrl != null){

            URL hubUrl = new URL(seleniumRemoteUrl);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            RemoteWebDriver webDriver;

            switch (driverManagerType){

                case CHROME:
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                    webDriver = new RemoteWebDriver(hubUrl, capabilities);
                    ScenarioContext.getInstance().setWebDriver(webDriver);
                    break;

                case FIREFOX:
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                    webDriver = new RemoteWebDriver(hubUrl, capabilities);
                    ScenarioContext.getInstance().setWebDriver(webDriver);
                    break;

                default:
                    throw new UnsupportedOperationException("Driver type " + driverManagerType + " is not supported !");
            }

            return;
        }

        String usingGrid = System.getProperty("usingZaleniumGrid");

        System.out.println("------------------------------------");
        System.out.println("USING ZALENIUM GRID IS SET TO " + usingGrid);
        System.out.println("------------------------------------");

        if(Boolean.parseBoolean(usingGrid)){

            URL hubUrl = new URL("http://localhost:4444/wd/hub");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            RemoteWebDriver webDriver;

            switch (driverManagerType){

                case CHROME:
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                    webDriver = new RemoteWebDriver(hubUrl, capabilities);
                    ScenarioContext.getInstance().setWebDriver(webDriver);
                    break;

                case FIREFOX:
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                    webDriver = new RemoteWebDriver(hubUrl, capabilities);
                    ScenarioContext.getInstance().setWebDriver(webDriver);
                    break;

                default:
                    throw new UnsupportedOperationException("Driver type " + driverManagerType + " is not supported !");
            }

            return;
        }

        switch (driverManagerType){

            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                ScenarioContext.getInstance().setWebDriver(new ChromeDriver(options));
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

            default:
                throw new UnsupportedOperationException("Driver type " + driverManagerType + " is not supported !");
        }
    }
}
