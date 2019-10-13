package com.apothuaud.automation.bdd.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ScenarioHooks {

    @Before
    public void initScenarioContext(){

        ScenarioContext.newInstance();
    }

    @After
    public void cleanDriver(){

        if(ScenarioContext.getInstance().getWebDriver() != null){
            ScenarioContext.getInstance().getWebDriver().quit();
        }
    }
}
