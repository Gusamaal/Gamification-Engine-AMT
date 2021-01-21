Feature: User operations on rules

  Background:
    Given there is a Gamification server

    Scenario: create a badge rule
      Given I have an application payload
      When I POST the application payload to the /applications endpoint
      Given I have a correct API key
      Given I have a badge rule payload
      When I POST the rule payload to the /rules endpoint
      Then I receive a 201 status code