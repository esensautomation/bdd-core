@Ignore @Wiki
Feature: Wiki

    Scenario: Prepare Api Request
        Given I want to use url template "{string}"
        Given I want to use url domain "{string}"

    Scenario: Send Api Request
        When I send the request
        When I send "{string}" request

    Scenario: Validate Api Response
        Then I should have response with status code "{string}"

    Scenario: Prepare web client
        Given I want to use "{string}" web driver

    Scenario: Web client actions
        When I fill web field "{string}" with text "{string}"

    Scenario: Web client validations
