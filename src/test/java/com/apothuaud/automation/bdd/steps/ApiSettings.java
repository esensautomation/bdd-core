package com.apothuaud.automation.bdd.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ApiSettings {

    @Given("I want to use request header {string} as {string}")
    public void iWantToUseHeaderAs(String headerName, String headerValue) {

        if(headerValue.contains("${")){

            int firstIndexVariable = headerValue.indexOf("${");
            int lastIndexVariable = headerValue.indexOf("}");
            String headerValueVariablePart = headerValue.substring(firstIndexVariable, lastIndexVariable + 1);
            String headerVariableName = headerValueVariablePart.replace("${", "").replace("}", "");
            String headerVariableValue = ScenarioContext.getInstance().getScenarioParams().get(headerVariableName);
            headerValue = headerValue.replace(headerValueVariablePart, headerVariableValue);
        }

        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();
        requestSpecification = requestSpecification.header(headerName, headerValue);
        ScenarioContext.getInstance().setRequestSpecification(requestSpecification);
    }

    @Given("I want to use request headers as")
    public void iWantToUseRequestHeadersAs(DataTable dt) {
        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
        for (Map<String, String> row : table) {
            iWantToUseHeaderAs(row.get("headerName"), row.get("headerValue"));
        }
    }

    @Given("I want to use request query param {string} as {string}")
    public void iWantToUseRequestQueryParamAs(String paramName, String paramValue) {
        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();
        requestSpecification = requestSpecification.queryParam(paramName, paramValue);
        ScenarioContext.getInstance().setRequestSpecification(requestSpecification);
    }

    @Given("I want to use request query params as")
    public void iWantToUseRequestQueryParamsAs(DataTable dt) {
        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
        for(Map<String, String> row : table) {
            iWantToUseRequestQueryParamAs(row.get("paramName"), row.get("paramValue"));
        }
    }

    @Given("I want to use request form param {string} as {string}")
    public void iWantToUseFormParamAs(String paramName, String paramValue) {
        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();
        requestSpecification = requestSpecification.formParam(paramName, paramValue);
        ScenarioContext.getInstance().setRequestSpecification(requestSpecification);
    }

    @Given("I want to use request form params as")
    public void iWantToUseRequestFormParamsAs(DataTable dt) {
        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
        for (Map<String, String> row : table) {
            iWantToUseFormParamAs(row.get("paramName"), row.get("paramValue"));
        }
    }

    @Given("I want to use request method {string}")
    public void iWantToUseRequestMethod(String requestMethod) {
        ScenarioContext.getInstance().setRequestMethod(Method.valueOf(requestMethod));
    }

    @Given("I want to use request json body from file {string}")
    public void iWantToUseRequestBodyFromFile(String filePath) {
        filePath = "src/test/resources/data/" + filePath;
        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();
        requestSpecification = requestSpecification.body(readLineByLineJava8(filePath));
        ScenarioContext.getInstance().setRequestSpecification(requestSpecification);
    }

    private static String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    @Given("I don't want to follow redirects")
    public void iDonTWantToFollowRedirects() {
        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();
        requestSpecification = requestSpecification.redirects().follow(false);
        ScenarioContext.getInstance().setRequestSpecification(requestSpecification);
    }

    @Given("I want to follow redirects")
    public void iWantToFollowRedirects() {
        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();
        requestSpecification = requestSpecification.redirects().follow(true);
        ScenarioContext.getInstance().setRequestSpecification(requestSpecification);
    }
}
