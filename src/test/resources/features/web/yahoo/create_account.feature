@WEB @Yahoo
Feature: [WEB][Yahoo] Create Account
  As a user of Yahoo web app
  I should be able to create an account

  @Nominal @Critical
  Scenario: Nominal Case
    # --- Step
    # --- n° 1
    # --- Open Yahoo Login Page
    Given I want to use url "https://login.yahoo.com/"
    And I want to use "Chrome" web driver
    When I open web client
    Then I should see web element "id->createacc"
    # --- Step
    # --- n° 2
    # --- Go to registration form
    When I click on web element "id->createacc"
    Then I should see web element "id->usernamereg-firstName"
    When I fill web field "id->usernamereg-firstName" with text "Esens"