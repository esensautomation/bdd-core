@MOBILE @ANDROID @Calculator @Ignore
Feature: [ANDROID][Calculator] Additions
  As a user of Android Calculator app
  I should be able to perform additions

  @Nominal @Critical
  Scenario Outline: Nominal cases
    Given I want to use appium hub url "http://localhost:4723/wd/hub"
    And I want to use appium platform name "Android"
    And I want to use appium device name "Nexus_4_API_27"
    And I want to use appium app "samples/mobile/android/calculator/apk/app-debug.apk"
    When I open android client
    Then I should see android element "id->com.example.eyakub.calculator:id/textView"
    When I click on android element "id->com.example.eyakub.calculator:id/buttonDel"
    And I click on android element "<number1>"
    And I click on android element "<operator>"
    And I click on android element "<number2>"
    And I click on android element "id->com.example.eyakub.calculator:id/buttoneql"
    Then I should see text "<result>" in android element "id->com.example.eyakub.calculator:id/textView"

    Examples:
      | number1                                      | operator                                       | number2                                      | result |
      | id->com.example.eyakub.calculator:id/button1 | id->com.example.eyakub.calculator:id/buttonadd | id->com.example.eyakub.calculator:id/button2 | 3.0    |
      | id->com.example.eyakub.calculator:id/button3 | id->com.example.eyakub.calculator:id/buttonsub | id->com.example.eyakub.calculator:id/button1 | 2.0    |
