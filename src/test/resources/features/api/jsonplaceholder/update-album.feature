@API @Jsonplaceholder
Feature: [API][Jsonplaceholder] Update an Album
  As a User of Jsonplaceholder API
  I should be able to update an album

  @Nominal @Critical
  Scenario: Nominal case
    Given I want to use url "https://jsonplaceholder.typicode.com/albums/1"
    And I want to use request headers as
      | headerName   | headerValue      |
      | Content-Type | application/json |
    And I want to use request json body from file "samples/api/jsonplaceholder/request/PUT-update-album.json"
    When I send "PUT" request
    Then I should have response with status code "200"
    And I should have json response with reference from file "samples/api/jsonplaceholder/response/PUT-update-album.json"