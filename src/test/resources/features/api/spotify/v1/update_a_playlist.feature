@API @Spotify
Feature: [API][Spotify] Update a Playlist
  As a user of Spotify Api
  I should be able to update Playlist details

  @Nominal @Critical
  Scenario: Nominal Case
    ## STEP 1 - Ask for authorization redirect uri
    ## -------------------------------------------
    Given I want to use url "https://accounts.spotify.com/authorize"
    And I want to use request query params as
      | paramName     | paramValue                              |
      | client_id     | 14ad74980cc24ecb9aff88ded35986e8        |
      | response_type | token                                   |
      | redirect_uri  | http://www.example.com/postman/redirect |
      | state         | 123                                     |
      | scope         | playlist-modify-public                  |
      | show_dialog   | false                                   |
    And I don't want to follow redirects
    When I send "GET" request
    Then I should have response with header "Location" present
    And I save response header "Location" as scenario param "authenticationRedirect"
    ## STEP 2 - Fill the login form using chrome client headless
    ## ---------------------------------------------------------
    Given I want to use "Chrome" web driver
    And I want to use url "${authenticationRedirect}"
    When I open web client
    And I fill web field "id->login-username" with text "esensqatraining@yopmail.com"
    And I fill web field "id->login-password" with text "Passwd1!"
    And I click on web element "id->login-button"
    And I wait for web condition "urlContains->#access_token="
    Then I save web client url in scenario param "urlAfterAuthentication"
    And I extract regexp "_token=(.*)&token_" from "urlAfterAuthentication" to "authenticationToken"
    ## STEP 3 - Update Playlist request
    ## --------------------------------
    Given I want to use url "https://api.spotify.com/v1/playlists/4g7UzQ4im7lbuEJhyEgJWJ"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
      | Content-Type  | application/json              |
    And I want to use request json body from file "samples/api/spotify/v1/request/PUT_update_playlist.json"
    When I send "PUT" request
    Then I should have response with status code "200"

  @ErrorCase @Critical
  Scenario: Error Case - no auth token
    Given I want to use url "https://api.spotify.com/v1/playlists/4g7UzQ4im7lbuEJhyEgJWJ"
    And I want to use request headers as
      | headerName   | headerValue      |
      | Content-Type | application/json |
    And I want to use request json body from file "samples/api/spotify/v1/request/PUT_update_playlist.json"
    When I send "PUT" request
    Then I should have response with json attributes equals to
      | jsonPath      | expected          |
      | error.message | No token provided |