@Api @Jsonplaceholder @release-1 @issue-1 @Albums
Feature: [Jsonplaceholder][Albums] Read Albums List
  As a User of Jsonplaceholder API
  I should be able to read albums list

  @MonoEP @GET @Nominal @Critical
  Scenario: Read albums
    Given I want to use url "https://jsonplaceholder.typicode.com/albums"
    When I send "GET" request
    Then I should have json response with reference from file "jsonplaceholder/response/GET-albums.json"