Feature: Example standalone UI

  Background: Prepare for scenario
    Given main page of Petclinic is opened in firefox

  @standalone
  Scenario: Find Owner
    When user click to tab find owners
    And type last name Davis
    And click button submit
    Then user should see result Betty Davis

  @standalone
  Scenario: Failed scenario
    Then failed assertion

