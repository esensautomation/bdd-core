package com.esens.automation.bdd.selector;

import org.openqa.selenium.By;

public class AndroidSelector {

    private SelectorType selectorType;
    private String selector;

    public AndroidSelector(String selectorExpr){

        String[] split = selectorExpr.split("->");
        selectorType = SelectorType.valueOf(split[0]);
        selector = split[1];
    }

    public By getAppiumSelector(){

        switch (selectorType){

            case id:

                return By.id(selector);

            case xpath:

                return By.xpath(selector);
        }

        return null;
    }
}
