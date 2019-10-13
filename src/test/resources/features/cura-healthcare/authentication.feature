@Web @CuraHealthcare @release-3 @issue-3 @Authentication
Feature: [CuraHealthcare] Authentication
  As a user of Cura Healthcare web app
  I should be able to authenticate

  @Chrome @Form @Nominal @Critical
  Scenario: Nominal Case
    Given I want to use "Chrome" driver
    And I want to use url "https://katalon-demo-cura.herokuapp.com/profile.php#login"
    When I open web driver
    And I fill field "id->txt-username" with "John Doe"
    And I fill field "id->txt-password" with "ThisIsNotAPassword"
    And I click on element "id->btn-login"
    And I wait for web condition "urlContains->#appointment"
    Then I should see element "id->combo_facility"
