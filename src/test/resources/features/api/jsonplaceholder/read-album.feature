@API @Jsonplaceholder
Feature: [API][Jsonplaceholder] Read album details
  As a User of Jsonplaceholder API
  I should be able to read album details

  @Nominal @Critical
  Scenario: Nominal case - v1
    Given I want to use url template "${PROTOCOL}://${DOMAIN}${ENDPOINT}"
    And I want to use url protocol "https"
    And I want to use url domain "jsonplaceholder.typicode.com"
    And I want to use url endpoint "/albums/1"
    And I want to use request method "GET"
    When I send the request
    Then I should have response with status code "200"
    And I should have response with json attribute "id" present
    And I should have response with json attribute "userId" present
    And I should have response with json attribute "title" present

  @ErrorCase @Critical
  Scenario: Read an album - v2
    Given I want to use url "https://jsonplaceholder.typicode.com/albums/14567890987654"
    When I send "GET" request
    Then I should have response with status code "404"