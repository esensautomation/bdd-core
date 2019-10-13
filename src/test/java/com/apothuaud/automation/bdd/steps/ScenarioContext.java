package com.apothuaud.automation.bdd.steps;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;

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
    private WebDriver webDriver;

    // Params
    private Properties secrets;
    private Map<String, String> scenarioParams;

    private static ScenarioContext instance = new ScenarioContext();

    private ScenarioContext(){

        setSecrets(new Properties());
        setScenarioParams(new HashMap<String, String>());
        setRequestSpecification(RestAssured
                .given()
                .config(RestAssuredConfig.newConfig().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))));
    }

    String getUrlFromScenario() {
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

    static ScenarioContext getInstance(){
        return instance;
    }

    private String getUrlTemplate() {
        return urlTemplate;
    }

    void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    private String getProtocol() {
        return protocol;
    }

    void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    private String getDomain() {
        return domain;
    }

    void setDomain(String domain) {
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

    void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    private String getCompleteUrl() {
        return completeUrl;
    }

    void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    Method getRequestMethod() {
        return requestMethod;
    }

    void setRequestMethod(Method requestMethod) {
        this.requestMethod = requestMethod;
    }

    RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    Response getResponse() {
        return response;
    }

    void setResponse(Response response) {
        this.response = response;
    }

    public Properties getSecrets() {
        return secrets;
    }

    private void setSecrets(Properties secrets) {
        this.secrets = secrets;
    }

    Map<String, String> getScenarioParams() {
        return scenarioParams;
    }

    private void setScenarioParams(Map<String, String> scenarioParams) {
        this.scenarioParams = scenarioParams;
    }

    WebDriver getWebDriver() {
        return webDriver;
    }

    void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
