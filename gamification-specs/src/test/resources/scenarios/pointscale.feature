Feature: User operations on pointscales

  Background:
    Given there is a Gamification server

    # note the singular in the pointscale endpoint name
  Scenario: create a pointscale
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a pointscale payload
    When I POST the pointscale payload to the /pointscale endpoint
    Then I receive a 201 status code
