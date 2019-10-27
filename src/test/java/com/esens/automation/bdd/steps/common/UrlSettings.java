package com.esens.automation.bdd.steps.common;

import com.esens.automation.bdd.steps.ScenarioContext;
import io.cucumber.java.en.Given;

public class UrlSettings {

    @Given("I want to use url {string}")
    public void iWantToUseUrl(String url) {

        if (url.contains("${") && url.contains("}")){

            if (url.startsWith("${") && url.endsWith("}")){

                url = url.replace("${", "").replace("}", "");
                url = ScenarioContext.getInstance().getScenarioParams().get(url);
            }

            else {

                int firstIndexVariable = url.indexOf("${");
                int lastIndexVariable = url.indexOf("}");
                String urlValueVariablePart = url.substring(firstIndexVariable, lastIndexVariable + 1);
                String urlVariableName = urlValueVariablePart.replace("${", "").replace("}", "");
                String urlVariableValue = ScenarioContext.getInstance().getScenarioParams().get(urlVariableName);
                url = url.replace(urlValueVariablePart, urlVariableValue);
            }

        }

        ScenarioContext.getInstance().setCompleteUrl(url);
    }

    @Given("I want to use url template {string}")
    public void iWantToUseUrlTemplate(String urlTemplate) {
        ScenarioContext.getInstance().setUrlTemplate(urlTemplate);
    }

    @Given("I want to use url protocol {string}")
    public void iWantToUseProtocol(String protocol) {
        ScenarioContext.getInstance().setProtocol(protocol);
    }

    @Given("I want to use url domain {string}")
    public void iWantToUseDomain(String domain) {
        ScenarioContext.getInstance().setDomain(domain);
    }

    @Given("I want to use url endpoint {string}")
    public void iWantToUseEndpoint(String endpoint) {
        ScenarioContext.getInstance().setEndpoint(endpoint);
    }
}
