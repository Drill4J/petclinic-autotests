Feature: Example standalone UI

  Background: Prepare for scenario
    Given main page of Petclinic is opened in chrome

  @standalone
  Scenario Outline: User click tabs and see headers
    When user click to tab <tab>
    Then user should see header <header>

    Examples: Pages
      | tab                                                 | header                |
      | home page                                           | Welcome               |
      | find owners                                         | Find Owners           |
      | veterinarians                                       | Veterinarians         |
      | trigger a RuntimeException to see how it is handled | Something happened... |

