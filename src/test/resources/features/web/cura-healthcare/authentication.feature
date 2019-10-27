@WEB @CuraHealthcare
Feature: [WEB][CuraHealthcare] Authentication
  As a user of Cura Healthcare web app
  I should be able to authenticate

  @Nominal @Critical
  Scenario: Nominal Case
    Given I want to use "Chrome" web driver
    And I want to use url "https://katalon-demo-cura.herokuapp.com/profile.php#login"
    When I open web client
    And I fill web field "id->txt-username" with text "John Doe"
    And I fill web field "id->txt-password" with text "ThisIsNotAPassword"
    And I click on web element "id->btn-login"
    And I wait for web condition "urlContains->#appointment"
    Then I should see web element "id->combo_facility"

  @ErrorCase @Critical
  Scenario: Error Case
    Given I want to use "Chrome" web driver
    And I want to use url "https://katalon-demo-cura.herokuapp.com/profile.php#login"
    When I open web client
    And I fill web field "id->txt-username" with text "azertyuiop"
    And I fill web field "id->txt-password" with text "azertyuiop"
    And I click on web element "id->btn-login"
    Then I should see web element "xpath->//p[contains(@class, 'text-danger')]"
    Then I should see text "Login failed!" in web element "xpath->//p[contains(@class, 'text-danger')]"