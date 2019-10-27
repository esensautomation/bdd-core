@API @Spotify
Feature: [API][Spotify] CRUD on Playlist
  As a user of Spotify Api
  I should be able to CRUD Playlists

  @Nominal
  Scenario: Nominal Case
    ## -
    ## -------------------------------------------
    ## STEP 1 - Ask for authorization redirect uri
    ## and store authentication redirect uri
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
    Then I should have response with header "Location" present
    And I save response header "Location" as scenario param "authenticationRedirect"
    ## -
    ## ---------------------------------------------------------
    ## STEP 2 - Fill the login form using chrome client headless
    ## and store authenticationToken
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
    ## -
    ## -------------------------------------------
    ## STEP 3 : get current user profile playlists
    ## and store user id from response
    ## -------------------------------------------
    Given I want to use url "https://api.spotify.com/v1/me"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    When I send "GET" request
    Then I should have response with status code "200"
    And I save response json attribute "id" as scenario param "userId"
    ## -
    ## ---------------------------------
    ## STEP 4 : get initial nb playlists
    ## ---------------------------------
    Given I want to use url "https://api.spotify.com/v1/me/playlists"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    And I want to use request query params as
      | paramName | paramValue |
      | limit     | 50         |
    When I send "GET" request
    Then I should have response with status code "200"
    And I save size of response json array "items" as scenario param "nbPlaylistsBefore"
    ## -
    ## --------------------------
    ## STEP 5 : create a playlist
    ## and store new playlist id
    ## --------------------------
    Given I want to use url "https://api.spotify.com/v1/users/${userId}/playlists"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
      | Content-Type  | application/json              |
    And I want to use request json body from file "samples/api/spotify/v1/request/POST_create_playlist.json"
    When I send "POST" request
    Then I should have response with status code "201"
    And I save response json attribute "id" as scenario param "newPlaylistId"
    ## -
    ## ---------------------------------
    ## STEP 6 : get new nb playlists
    ## ---------------------------------
    Given I want to use url "https://api.spotify.com/v1/me/playlists"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    And I want to use request query params as
      | paramName | paramValue |
      | limit     | 50         |
    When I send "GET" request
    Then I should have response with status code "200"
    And I save size of response json array "items" as scenario param "nbPlaylistsAfter"
    And I should have scenario param "nbPlaylistsAfter" greater than scenario param "nbPlaylistsBefore"
    ## -
    ## ----------------------------------
    ## STEP 7 : read new playlist details
    ## and store initial nb tracks
    ## ----------------------------------
    Given I want to use url "https://api.spotify.com/v1/playlists/${newPlaylistId}"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    When I send "GET" request
    Then I should have response with status code "200"
    And I save size of response json array "tracks.items" as scenario param "nbTracksBefore"
    ## -
    ## ------------------------------
    ## STEP 8 : add track to playlist
    ## ------------------------------
    Given I want to use url "https://api.spotify.com/v1/playlists/${newPlaylistId}/tracks"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
      | Content-Type  | application/json              |
    And I want to use request json body from file "samples/api/spotify/v1/request/POST_add_tracks.json"
    When I send "POST" request
    Then I should have response with status code "201"
    ## -
    ## -----------------------------
    ## STEP 9 : read playlist tracks
    ## and store nb tracks after
    ## -----------------------------
    Given I want to use url "https://api.spotify.com/v1/playlists/${newPlaylistId}/tracks"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    When I send "GET" request
    Then I should have response with status code "200"
    And I save size of response json array "items" as scenario param "nbTracksAfter"
    And I should have scenario param "nbTracksAfter" greater than scenario param "nbTracksBefore"
    ## -
    ## ---------------------------------
    ## STEP 10 : change playlist details
    ## ---------------------------------
    Given I want to use url "https://api.spotify.com/v1/playlists/${newPlaylistId}"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
      | Content-Type  | application/json              |
    And I want to use request json body from file "samples/api/spotify/v1/request/PUT_update_playlist.json"
    When I send "PUT" request
    Then I should have response with status code "200"
    ## -
    ## -----------------------------------
    ## STEP 11 : Read the playlist details
    ## and verify changes applied
    ## -----------------------------------
    Given I want to use url "https://api.spotify.com/v1/playlists/${newPlaylistId}"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    When I send "GET" request
    Then I should have response with status code "200"
    And I should have response with json attributes equals to
      | jsonPath      | expected                |
      | name          | My Training Playlist    |
      | description   | Updated one more time ! |
      | public        | true                    |
      | collaborative | false                   |
    ## -
    ## ------------------------------
    ## STEP 12 : Unfollow the playlit
    ## ------------------------------
    Given I want to use url "https://api.spotify.com/v1/playlists/${newPlaylistId}/followers"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    When I send "DELETE" request
    Then I should have response with status code "200"
    ## -
    ## ---------------------------------
    ## STEP 13 : get new nb playlists
    ## ---------------------------------
    Given I want to use url "https://api.spotify.com/v1/me/playlists"
    And I want to use request headers as
      | headerName    | headerValue                   |
      | Authorization | Bearer ${authenticationToken} |
    And I want to use request query params as
      | paramName | paramValue |
      | limit     | 50         |
    When I send "GET" request
    Then I should have response with status code "200"
    And I save size of response json array "items" as scenario param "nbPlaylistsAfter"
    And I should have scenario param "nbPlaylistsAfter" equals to scenario param "nbPlaylistsBefore"

