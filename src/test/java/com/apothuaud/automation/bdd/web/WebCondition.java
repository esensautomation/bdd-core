package com.apothuaud.automation.bdd.web;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WebCondition {

    private WebConditionType webConditionType;
    private String[] conditionParams;

    public WebCondition(String webConditionExpr){

        String[] split = webConditionExpr.split("->");
        webConditionType = WebConditionType.valueOf(split[0]);
        conditionParams = split[1].replace(" ", "").split(",");
    }

    public ExpectedCondition getExpectedCondition(){

        switch (webConditionType){

            case urlContains:

                return ExpectedConditions.urlContains(conditionParams[0]);
        }

        return null;
    }
}
