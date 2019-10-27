package com.esens.automation.bdd.steps.api;

import com.esens.automation.bdd.steps.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ApiValidations {

    /**
     * Save response header by name into scenario parameters map
     *
     * @param headerName name of header to be saved
     * @param scenarioParamName name of parameter in scenario parameters Map
     */
    @Then("I save response header {string} as scenario param {string}")
    public void iSaveResponseHeaderAsScenarioParam(String headerName, String scenarioParamName) {

        Response response = ScenarioContext.getInstance().getResponse();

        String headerValue = response.getHeader(headerName);

        ScenarioContext.getInstance().getScenarioParams().put(scenarioParamName, headerValue);
    }

    /**
     * Save response json attribute by jsonPath into scenario parameters Map
     *
     * @param jsonPath the path of json param to be saved
     * @param scenarioParamName name of parameter in scenario parameters Map
     */
    @Then("I save response json attribute {string} as scenario param {string}")
    public void iSaveResponseJsonAttributeAsScenarioParam(String jsonPath, String scenarioParamName) {

        Response response = ScenarioContext.getInstance().getResponse();

        // Get JSON Representation from Response Body
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document
        String value = jsonPathEvaluator.getString(jsonPath);

        ScenarioContext.getInstance().getScenarioParams().put(scenarioParamName, value);
    }

    /**
     * Save size of json array in response as scenario parameter
     *
     * @param jsonPath path of the array in response
     * @param scenarioParamName name of the parameter to be saved
     */
    @Then("I save size of response json array {string} as scenario param {string}")
    public void iSaveSizeOfResponseJsonArrayAsScenarioParam(String jsonPath, String scenarioParamName) {

        Response response = ScenarioContext.getInstance().getResponse();

        // Get JSON Representation from Response Body
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document
        int value = jsonPathEvaluator.getList(jsonPath).size();

        ScenarioContext.getInstance().getScenarioParams().put(scenarioParamName, String.valueOf(value));
    }

    /**
     * Compares the api response with a json file
     *
     * @param filePath path of the json reference file
     */
    @And("I should have json response with reference from file {string}")
    public void iShouldHaveResponseJsonWithReferenceFromFile(String filePath) {
        JsonPath actual = ScenarioContext.getInstance().getResponse().getBody().jsonPath();
        JsonPath expected = JsonPath.from(new File("src/test/resources/data/" + filePath));
        Object actualObject = actual.getJsonObject("");
        Object expectedObject = expected.getJsonObject("");
        Assertions.assertThat(actualObject).isEqualTo(expectedObject);
    }

    /**
     * Verify presence of the requested header in response headers
     *
     * @param headerName name of requested header to be present
     */
    @Then("I should have response with header {string} present")
    public void iShouldHaveResponseHeaderPresent(String headerName) {

        Response response = ScenarioContext.getInstance().getResponse();

        String headerValue = response.getHeader(headerName);

        Assertions.assertThat(headerValue).isNotNull();
    }

    /**
     *
     * @param jsonPath
     * @param expected
     */
    @Then("I should have response with json attribute {string} equals to {string}")
    public void iShouldHaveResponseJsonAttributeEqualsTo(String jsonPath, String expected) {

        Response response = ScenarioContext.getInstance().getResponse();

        // Get JSON Representation from Response Body
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document
        String actual = jsonPathEvaluator.getString(jsonPath);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    /**
     *
     * @param jsonPath
     */
    @Then("I should have response with json attribute {string} present")
    public void iShouldHaveResponseJsonAttributePresent(String jsonPath) {

        Response response = ScenarioContext.getInstance().getResponse();

        // Get JSON Representation from Response Body
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document
        Object actual = jsonPathEvaluator.get(jsonPath);

        Assertions.assertThat(actual).isNotNull();
    }

    /**
     *
     * @param dt
     */
    @Then("I should have response with json attributes equals to")
    public void iShouldHaveResponseJsonAttributesEqualsTo(DataTable dt) {
        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
        for (Map<String, String> row : table) {
            iShouldHaveResponseJsonAttributeEqualsTo(row.get("jsonPath"), row.get("expected"));
        }
    }

    /**
     *
     * @param dt
     */
    @Then("I should have response with json attributes present")
    public void iShouldHaveResponseJsonAttributesPresent(DataTable dt) {
        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
        for (Map<String, String> row : table) {
            iShouldHaveResponseJsonAttributePresent(row.get("jsonPath"));
        }
    }







    @Then("I should have response with status code {string}")
    public void iShouldHaveResponseWithStatusCode(String expected) {

        Response response = ScenarioContext.getInstance().getResponse();

        String actual = String.valueOf(response.getStatusCode());

        Assertions.assertThat(actual).isEqualTo(expected);
    }




}
