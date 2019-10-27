package com.esens.automation.bdd.steps.api;

import com.esens.automation.bdd.steps.ScenarioContext;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * API Actions Steps Definition
 *
 * All steps that represent api actions are defined here.
 *
 */
public class ApiActions {

    /**
     * Send the api request with given request method
     *
     * @param requestMethod request method to be used
     */
    @When("I send {string} request")
    public void iSendRequest(String requestMethod) {

        // set request method in scenario context
        ScenarioContext.getInstance().setRequestMethod(Method.valueOf(requestMethod));

        // Send the request
        iSendTheRequest();
    }

    /**
     * Send the api request
     */
    @When("I send the request")
    public void iSendTheRequest() {

        // Get request specification from scenario context
        RequestSpecification requestSpecification = ScenarioContext.getInstance().getRequestSpecification();

        // Log request specification
        System.out.println("\n" +
                "*********************\n" +
                "Below is API request :\n" +
                "*********************\n");
        requestSpecification.log().all();

        // Get url from scenario context
        String url = ScenarioContext.getInstance().getUrlFromScenario();

        // Send the request and get response
        Response response = sendRequest(requestSpecification, url);

        // Log response
        System.out.println("\n" +
                "*********************\n" +
                "Below is API response :\n" +
                "*********************\n");
        response.then().log().all();

        // Save response in scenario context
        ScenarioContext.getInstance().setResponse(response);

        // Clear request specification in scenario context
        // enables further steps to be independent
        iClearScenarioRequestSpecification();
    }

    /**
     * Method to send a request specification and get response.
     *
     * @param requestSpecification the request specification to be sent
     * @param url the request url
     * @return the api response
     */
    private Response sendRequest(RequestSpecification requestSpecification, String url) {

        // set response to null
        Response response = null;

        // depending on request method
        switch (ScenarioContext.getInstance().getRequestMethod()){

            // GET request
            case GET:
                response = requestSpecification.get(url);
                break;

            // POST request
            case POST:
                response = requestSpecification.post(url);
                break;

            // PUT request
            case PUT:
                response = requestSpecification.put(url);
                break;

            // DELETE request
            case DELETE:
                response = requestSpecification.delete(url);
                break;
        }

        // verify that we have a response
        assert response != null;

        // throw response back
        return response;
    }

    /**
     * Clear request specification in scenario context
     */
    private void iClearScenarioRequestSpecification() {

        // Reset request specification
        ScenarioContext.getInstance().setRequestSpecification(RestAssured
                .given()
                .config(RestAssuredConfig.newConfig().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))));
    }
}
