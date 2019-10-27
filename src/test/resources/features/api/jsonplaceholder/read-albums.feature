@API @Jsonplaceholder
Feature: [API][Jsonplaceholder] Read Albums List
  As a User of Jsonplaceholder API
  I should be able to read albums list

  @Nominal @Critical
  Scenario: Nominal case
    Given I want to use url "https://jsonplaceholder.typicode.com/albums"
    When I send "GET" request
    Then I should have json response with reference from file "samples/api/jsonplaceholder/response/GET-albums.json"