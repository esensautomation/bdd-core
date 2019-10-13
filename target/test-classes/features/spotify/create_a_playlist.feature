@Api @Spotify @release-2 @feature-5 @Playlist
Feature: [Spotify][Playlists] Create a Playlist
  As a user of Spotify Api
  I should be able to create Playlists

  @MonoEP @POST @Nominal @Critical
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
      | scope         | playlist-modify-private                 |
      | show_dialog   | false                                   |
    And I don't want to follow redirects
    When I send "GET" request
    Then I should have response header "Location" present
    And I save response header "Location" as scenario param "authenticationRedirect"
    ## STEP 2 - Fill the login form using chrome client headless
    ## ---------------------------------------------------------
    Given I want to use "Chrome" driver
    And I want to use url "${authenticationRedirect}"
    When I open web driver
    And I fill field "id->login-username" with "esensqatraining@yopmail.com"
    And I fill field "id->login-password" with "Passwd1!"
    And I click on element "id->login-button"
    And I wait for web condition "urlContains->#access_token="
    Then I save driver url in scenario params as "urlAfterAuthentication"
    And I extract regexp "_token=(.*)&token_" from "urlAfterAuthentication" to "authenticationToken"
    ## STEP 3 - Create Playlist request
    ## --------------------------------
    Given I want to use url "https://api.spotify.com/v1/users/2g5o4vq6ayxp9eo5xlqw6os2k/playlists"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
      | Content-Type  | application/json              |
    And I want to use request json body from file "spotify/request/POST_create_playlist.json"
    When I send "POST" request
    Then I should have response with status code "201"
    And I should have response json attributes present
      | jsonPath      |
      | external_urls |
      | followers     |
      | href          |
      | images        |
      | owner         |
      | tracks        |
    And I should have response json attributes equals to
      | jsonPath      | expected                      |
      | name          | My Training Playlist          |
      | description   | Playlist for automation tests |
      | public        | true                          |
      | collaborative | false                         |

  @MonoEP @POST @ErrorCase @Critical
  Scenario: Error Case - no auth token
    And I want to use url "https://api.spotify.com/v1/users/2g5o4vq6ayxp9eo5xlqw6os2k/playlists"
    And I want to use request headers as
      | headerName   | headerValue      |
      | Content-Type | application/json |
    And I want to use request json body from file "spotify/request/POST_create_playlist.json"
    When I send "POST" request
    Then I should have response with status code "401"
    And I should have response json attributes equals to
      | jsonPath      | expected          |
      | error.message | No token provided |
