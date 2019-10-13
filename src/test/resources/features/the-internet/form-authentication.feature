@Web @TheInternet @release-3 @issue-4 @Authentication
Feature: [TheInternet] Form Authentication
  As a user of TheInternet web app
  I should be able to authenticate using form

  @Chrome @Form @Nominal @Critical
  Scenario: Nominal Case
    Given I want to use "Chrome" driver
    And I want to use url "https://the-internet.herokuapp.com/login"
    When I open web driver
    And I fill field "id->username" with "tomsmith"
    And I fill field "id->password" with "SuperSecretPassword!"
    And I click on element "xpath->//button//i[contains(text(), ' Login')]"
    And I wait for web condition "urlContains->secure"
    Then I should see element "xpath->//a//i[contains(text(), ' Logout')]"
