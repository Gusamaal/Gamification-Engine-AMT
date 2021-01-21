Feature: User operations on applications

  Background:
    Given there is a Gamification server

  Scenario: create a user
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    Then I receive a 201 status code

  Scenario: get one user
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    When I send a GET to the /user endpoint
    Then I receive a 200 status code
    And I receive a payload that correspond to the user payload

  Scenario: get the list of users
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    When I send a GET to the /users endpoint
    Then I receive a 200 status code

    #if no user it still works, but steps try to get first reputation therofore steps crashes if no user
  Scenario: get all users reputation
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    When I send a GET to the users-reputations endpoint
    Then I receive a 200 status code

    # warning the last and is not complete
  Scenario: get all users reputation with real user, badge and pointscale
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint
    Given I have a badge event payload
    When I POST the badge event payload to the /events endpoint
    When I send a GET to the users-reputations endpoint
    Then I receive a 200 status code
    And I receive a payload that correspond to all payloads

        #if no badges it still works, but steps try to get first badge therofore steps crashes if no badge
  Scenario: get an badge list from a user id
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint
    Given I have a badge event payload
    When I POST the badge event payload to the /events endpoint
    When I send a GET to the users-id-badges endpoint
    Then I receive a 200 status code

    #we only test one badge cause we dont have a sort mechanism
  Scenario: get same badge than the one we gave
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    Given I have a badge payload
    When I POST the badge payload to the /badges endpoint
    Given I have a badge rule payload
    When I POST the rule payload to the /rules endpoint
    Given I have a badge event payload
    When I POST the badge event payload to the /events endpoint
    When I send a GET to the users-id-badges endpoint
    Then I receive a 200 status code
    And I receive a badge that corresponds to the payload

        #we only test one pointscale cause we dont have a sort mechanism
  Scenario: get same pointscale total than the one we had
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a second pointscale payload
    When I POST the pointscale payload to the /pointscale endpoint
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    When I send a GET to the users-id-pointscale endpoint
    Then I receive a 200 status code
    And I receive an unchanged pointscale total


  Scenario: get a poinscale list from a user id
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Given I have a correct API key
    Given I have a pointscale payload
    When I POST the pointscale payload to the /pointscale endpoint
    Given I have a point rule payload
    When I POST the rule payload to the /rules endpoint
    Given I have a user payload
    When I POST the user payload to the /users endpoint
    Given I have a poinscale event payload
    When I POST the pointscale event payload to the /events endpoint
    When I send a GET to the users-id-pointscale endpoint
    Then I receive a 200 status code
    And I receive a pointscale that corresponds to the payload

  Scenario: get a reputation from a user username




