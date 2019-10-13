@Api @Jsonplaceholder @release-1 @feature-2 @Albums
Feature: [Jsonplaceholder][Albums] Delete an Album
  As a User of Jsonplaceholder API
  I should be able to delete albums

  @MonoEP @DELETE @Nominal @Critical
  Scenario: Delete an album - v1
    Given I want to use url template "${PROTOCOL}://${DOMAIN}${ENDPOINT}"
    And I want to use url protocol "https"
    And I want to use url domain "jsonplaceholder.typicode.com"
    And I want to use url endpoint "/albums/1"
    And I want to use request method "DELETE"
    When I send the request
    Then I should have response with status code "200"

  @MonoEP @DELETE @Nominal @Critical
  Scenario: Delete an album - v2
    Given I want to use url "https://jsonplaceholder.typicode.com/albums/1"
    When I send "DELETE" request
    Then I should have response with status code "200"