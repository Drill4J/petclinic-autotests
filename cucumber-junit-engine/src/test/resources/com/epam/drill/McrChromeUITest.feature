Feature: Example microservices pet clinic UI

  Background: Prepare for scenario
    Given main page of Petclinic is opened in chrome

  @mcr
  Scenario Outline: User open link and see header
    When user open <link>
    Then user should see page info <header>

    Examples: Pages
      | link                         | header               |
      | /#!/welcome                  | Welcome to Petclinic |
      | /#!/owners                   | Owners               |
      | /#!/owners/10/pets/13/visits | Visits               |
      | /#!/vets                     | Veterinarians        |

