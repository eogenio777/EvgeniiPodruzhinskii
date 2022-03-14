Feature: Use different elements page functionality
  Scenario: Different Elements Page test
    Given I open JDI GitHub site
    And I perform login with username 'Roman' and password 'Jdi1234'
    When I click on 'Service' button in Header
    And I click on 'Different Elements' button in Service dropdown
    And I select checkboxes 'Water, Wind'
    And I select radio 'Selen'
    And I select in dropdown 'Yellow'
    Then for each checkbox there is an individual log row and value is corresponded to the status of checkbox
    And for radio button there is a log row and value is corresponded to the status of radio button
    And for dropdown there is a log row and value is corresponded to the selected value
