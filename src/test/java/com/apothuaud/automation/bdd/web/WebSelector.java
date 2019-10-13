package com.apothuaud.automation.bdd.web;

import org.openqa.selenium.By;

public class WebSelector {

    private SelectorType selectorType;
    private String selector;

    public WebSelector(String selectorExpr){

        String[] split = selectorExpr.split("->");
        selectorType = SelectorType.valueOf(split[0]);
        selector = split[1];
    }

    public By getSeleniumSelector(){

        switch (selectorType){

            case id:

                return By.id(selector);

            case xpath:

                return By.xpath(selector);
        }

        return null;
    }
}
