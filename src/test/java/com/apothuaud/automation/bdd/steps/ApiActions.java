package com.apothuaud.automation.bdd.steps;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Actions Steps Definition
 *
 * All steps that represent actions are defined here.
 *
 */
public class ApiActions {

    /**
     * Send the api request
     */
    @When("I send the request")
    public void iSendTheRequest() {

        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();

        System.out.println("\n" +
                "*********************\n" +
                "Below is API request :\n" +
                "*********************\n");

        requestSpecification.log().all();

        String url = ScenarioContext.getInstance().getUrlFromScenario();

        Response response = sendResponse(requestSpecification, url);

        System.out.println("\n" +
                "*********************\n" +
                "Below is API response :\n" +
                "*********************\n");

        response.then().log().all();

        ScenarioContext.getInstance().setResponse(response);

        iClearScenarioRequestSpecification();
    }

    private Response sendResponse(RequestSpecification requestSpecification, String url) {

        Response response = null;

        switch (ScenarioContext.getInstance().getRequestMethod()){

            case GET:
                response = requestSpecification.get(url);
                break;
            case POST:
                response = requestSpecification.post(url);
                break;
            case PUT:
                response = requestSpecification.put(url);
                break;
            case DELETE:
                response = requestSpecification.delete(url);
                break;
        }

        assert response != null;

        return response;
    }

    @When("I send {string} request")
    public void iSendRequest(String requestMethod) {
        ScenarioContext.getInstance().setRequestMethod(Method.valueOf(requestMethod));
        iSendTheRequest();
    }

    private void iClearScenarioRequestSpecification() {

        ScenarioContext.getInstance().setRequestSpecification(RestAssured
                .given()
                .config(RestAssuredConfig.newConfig().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))));
    }
}
