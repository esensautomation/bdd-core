package com.esens.automation.bdd.steps;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Context Object to be shared between step definitions
 *
 * It holds all scenario data
 */
public class ScenarioContext {

    // URL config
    private String urlTemplate;
    private String protocol;
    private String domain;
    private int port;
    private String endpoint;
    private String completeUrl;

    // API config
    private Method requestMethod;
    private RequestSpecification requestSpecification;
    private Response response;

    // Web client config
    private URL seleniumHubUrl;
    private DesiredCapabilities seleniumCapabilities;
    private RemoteWebDriver webDriver;

    // Appium config
    private URL appiumHubUrl;
    private DesiredCapabilities appiumCapabilities;
    private MobileDriver<MobileElement> mobileDriver;
    private AndroidDriver<AndroidElement> androidDriver;
    private IOSDriver<IOSElement> iosDriver;

    // Params
    private Properties secrets;
    private Map<String, String> scenarioParams;

    private static ScenarioContext instance = new ScenarioContext();

    public ScenarioContext(){

        setSecrets(new Properties());
        setScenarioParams(new HashMap<String, String>());
        setRequestSpecification(RestAssured
                .given()
                .config(RestAssuredConfig.newConfig().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))));
        setAppiumCapabilities(new DesiredCapabilities());
        setSeleniumCapabilities(new DesiredCapabilities());
    }

    public String getUrlFromScenario() {
        // Get the url template from ScenarioContext
        String url = ScenarioContext.getInstance().getUrlTemplate();

        // If the scenario is not designed to use url template
        if (url == null) {

            // simply get url complete url from scenario
            url = ScenarioContext.getInstance().getCompleteUrl();
        }

        // The scenario is designed to use url template
        else {

            if (ScenarioContext.getInstance().getProtocol() != null) {

                url = url.replace("${PROTOCOL}", ScenarioContext.getInstance().getProtocol());
            }

            if(ScenarioContext.getInstance().getDomain() != null){

                url = url.replace("${DOMAIN}", ScenarioContext.getInstance().getDomain());
            }

            if(ScenarioContext.getInstance().getPort() > 0){

                url = url.replace("${PORT}", String.valueOf(ScenarioContext.getInstance().getPort()));
            }

            if(ScenarioContext.getInstance().getEndpoint() != null){

                url = url.replace("${ENDPOINT}", ScenarioContext.getInstance().getEndpoint());
            }
        }

        return url;
    }

    static void newInstance() {
        instance = new ScenarioContext();
    }

    public static ScenarioContext getInstance(){
        return instance;
    }

    private String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    private String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    private String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    private int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    private String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public Method getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(Method requestMethod) {
        this.requestMethod = requestMethod;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Properties getSecrets() {
        return secrets;
    }

    private void setSecrets(Properties secrets) {
        this.secrets = secrets;
    }

    public Map<String, String> getScenarioParams() {
        return scenarioParams;
    }

    private void setScenarioParams(Map<String, String> scenarioParams) {
        this.scenarioParams = scenarioParams;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public URL getAppiumHubUrl() {
        return appiumHubUrl;
    }

    public void setAppiumHubUrl(URL appiumHubUrl) {
        this.appiumHubUrl = appiumHubUrl;
    }

    public URL getSeleniumHubUrl() {
        return seleniumHubUrl;
    }

    public void setSeleniumHubUrl(URL seleniumHubUrl) {
        this.seleniumHubUrl = seleniumHubUrl;
    }

    public DesiredCapabilities getSeleniumCapabilities() {
        return seleniumCapabilities;
    }

    public void setSeleniumCapabilities(DesiredCapabilities seleniumCapabilities) {
        this.seleniumCapabilities = seleniumCapabilities;
    }

    public DesiredCapabilities getAppiumCapabilities() {
        return appiumCapabilities;
    }

    public void setAppiumCapabilities(DesiredCapabilities appiumCapabilities) {
        this.appiumCapabilities = appiumCapabilities;
    }

    public MobileDriver<MobileElement> getMobileDriver() {
        return mobileDriver;
    }

    public void setMobileDriver(MobileDriver<MobileElement> mobileDriver) {
        this.mobileDriver = mobileDriver;
    }

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return androidDriver;
    }

    public void setAndroidDriver(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public IOSDriver<IOSElement> getIosDriver() {
        return iosDriver;
    }

    public void setIosDriver(IOSDriver<IOSElement> iosDriver) {
        this.iosDriver = iosDriver;
    }
}
