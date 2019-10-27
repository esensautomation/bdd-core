@WEB @CuraHealthcare
Feature: [WEB][CuraHealthcare] Appointment
  As a user of Cura Healthcare web app
  I should be able to make appointments

  @Nominal @Critical
  Scenario: Unauthentified appointment
    Given I want to use "Chrome" web driver
    And I want to use url "https://katalon-demo-cura.herokuapp.com/"
    When I open web client
    Then I should see web element "id->btn-make-appointment"
    When I click on web element "id->btn-make-appointment"
    Then I should see web element "id->txt-username"
    When I fill web field "id->txt-username" with text "John Doe"
    And I fill web field "id->txt-password" with text "ThisIsNotAPassword"
    And I click on web element "id->btn-login"
    And I wait for web condition "urlContains->#appointment"
    Then I should see web element "id->btn-book-appointment"
    When I click on web element "id->btn-book-appointment"
    Then I should see web element "xpath->//div[contains(@class, 'datepicker')]"
    When I click on web element "xpath->/html/body/div/div[1]/table/thead/tr[2]/th[3]"
    And I click on web element "xpath->/html/body/div/div[1]/table/tbody/tr[1]/td[3]"
    And I click on web element "id->btn-book-appointment"
    And I wait for web condition "urlContains->#summary"
    Then I should see web element "id->facility"
    When I navigate to url "https://katalon-demo-cura.herokuapp.com/history.php#history"
    Then I should see web element "id->history"
