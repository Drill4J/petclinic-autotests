Feature: Example microservices pet clinic UI

  Background: Prepare for scenario
    Given main page of Petclinic is opened in firefox

  @mcr
  Scenario: Find Owner
    When user search owner by Davis
    Then he find owner Betty Davis

  @mcr
  Scenario: Failed scenario
    Then failed assertion
