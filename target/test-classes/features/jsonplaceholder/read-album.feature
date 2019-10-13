@Api @Jsonplaceholder @release-1 @feature-3 @Albums
Feature: [Jsonplaceholder][Albums] Read an Album
  As a User of Jsonplaceholder API
  I should be able to read album details

  @MonoEP @GET @Nominal @Critical
  Scenario: Read an album - v1
    Given I want to use url template "${PROTOCOL}://${DOMAIN}${ENDPOINT}"
    And I want to use url protocol "https"
    And I want to use url domain "jsonplaceholder.typicode.com"
    And I want to use url endpoint "/albums/1"
    And I want to use request method "GET"
    When I send the request
    Then I should have response with status code "200"
    And I should have response json attribute "id" present
    And I should have response json attribute "userId" present
    And I should have response json attribute "title" present

  @MonoEP @GET @Nominal @Critical
  Scenario: Read an album - v2
    Given I want to use url "https://jsonplaceholder.typicode.com/albums/1"
    When I send "GET" request
    Then I should have json response with reference from file "jsonplaceholder/response/GET-album-1.json"