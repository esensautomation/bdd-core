@WEB @TheInternet
Feature: [WEB][TheInternet] Form Authentication
  As a user of TheInternet web app
  I should be able to authenticate using form

  @Nominal @Critical
  Scenario: Nominal Case
    Given I want to use "Chrome" web driver
    And I want to use url "https://the-internet.herokuapp.com/login"
    When I open web client
    And I fill web field "id->username" with text "tomsmith"
    And I fill web field "id->password" with text "SuperSecretPassword!"
    And I click on web element "xpath->//button//i[contains(text(), ' Login')]"
    And I wait for web condition "urlContains->secure"
    Then I should see web element "xpath->//a//i[contains(text(), ' Logout')]"

  @ErrorCase @Critical
  Scenario: Error Case - Invalid username
    Given I want to use "Chrome" web driver
    And I want to use url "https://the-internet.herokuapp.com/login"
    When I open web client
    And I fill web field "id->username" with text "nottomsmith"
    And I fill web field "id->password" with text "SuperSecretPassword!"
    And I click on web element "xpath->//button//i[contains(text(), ' Login')]"
    Then I should see web element "id->flash"
    And I should see text "Your username is invalid!" in web element "id->flash"

  @ErrorCase @Critical
  Scenario: Error Case - Invalid password
    Given I want to use "Chrome" web driver
    And I want to use url "https://the-internet.herokuapp.com/login"
    When I open web client
    And I fill web field "id->username" with text "tomsmith"
    And I fill web field "id->password" with text "NotSuperSecretPassword!"
    And I click on web element "xpath->//button//i[contains(text(), ' Login')]"
    Then I should see web element "id->flash"
    And I should see text "Your password is invalid!" in web element "id->flash"
