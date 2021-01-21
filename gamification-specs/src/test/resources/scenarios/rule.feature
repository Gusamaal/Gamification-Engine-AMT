Feature: User operations on rules

  Background:
    Given there is a Gamification server

    # warning: this test ref a badge that doesn't exist
  Scenario: create a badge rule with a badge that doesn't exist
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint
    Then I receive a 201 status code

  Scenario: create a badge rule with a badge that exists
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint
    Then I receive a 201 status code

    #this get may get an empty list but thats okay
  Scenario: get the list of rules
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    When I send a GET to the /rules endpoint
    Then I receive a 200 status code