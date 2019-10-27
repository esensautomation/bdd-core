@WEB @TheInternet
Feature: [WEB][TheInternet] Checkboxes
  As a user of bdd core framework
  I should be able to check and validate checkboxes

  @Nominal @Critical
  Scenario: Play with checkboxes
    Given I want to use "Chrome" web driver
    And I want to use url "https://the-internet.herokuapp.com/checkboxes"
    When I open web client
    And I click on web element "xpath->//*[@id='checkboxes']/input[1]"
    Then I should see web element "xpath->//*[@id='checkboxes']/input[1]" selected
    And I should see web element "xpath->//*[@id='checkboxes']/input[2]" selected
