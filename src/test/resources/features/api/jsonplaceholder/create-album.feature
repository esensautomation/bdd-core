@API @Jsonplaceholder
Feature: [API][Jsonplaceholder] Create albums
  As a User of Jsonplaceholder API
  I should be able to create albums

  @Nominal @Critical
  Scenario: Nominal case - v1
    Given I want to use url template "${PROTOCOL}://${DOMAIN}${ENDPOINT}"
    And I want to use url protocol "https"
    And I want to use url domain "jsonplaceholder.typicode.com"
    And I want to use url endpoint "/albums"
    And I want to use request header "Content-Type" as "application/x-www-form-urlencoded"
    And I want to use request form param "userId" as "1"
    And I want to use request form param "title" as "My album title"
    And I want to use request method "POST"
    When I send the request
    Then I should have response with status code "201"
    And I should have response with json attribute "userId" equals to "1"
    And I should have response with json attribute "title" equals to "My album title"
    And I should have response with json attribute "id" present

  @Nominal @Critical
  Scenario: Nominal case - v2
    Given I want to use url "https://jsonplaceholder.typicode.com/albums"
    And I want to use request header "Content-Type" as "application/x-www-form-urlencoded"
    And I want to use request form params as
      | paramName | paramValue     |
      | userId    | 1              |
      | title     | My album title |
    When I send "POST" request
    Then I should have response with status code "201"
    And I should have response with json attributes equals to
      | jsonPath | expected       |
      | userId   | 1              |
      | title    | My album title |

  @Nominal @Critical
  Scenario: Nominal case - v3
    Given I want to use url "https://jsonplaceholder.typicode.com/albums"
    And I want to use request headers as
      | headerName   | headerValue      |
      | Content-Type | application/json |
    And I want to use request json body from file "samples/api/jsonplaceholder/request/POST-create-album.json"
    When I send "POST" request
    Then I should have response with status code "201"
    And I should have json response with reference from file "samples/api/jsonplaceholder/response/POST-create-album.json"