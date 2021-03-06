Feature: Basic operations on applications

  Background:
    Given there is a Gamification server

  Scenario: create an application
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Then I receive a 201 status code


  Scenario: get the application info
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    When I send a GET to the /applications endpoint with an API Key
    Then I receive a 200 status code
    And I receive a payload that corresponds to the application payload


    # this is also a comment like python
  Scenario: get the application info without the appropriate key
    Given I have a random API Key
    When I send a GET to the /applications endpoint with an API Key
    Then I receive a 403 status code


