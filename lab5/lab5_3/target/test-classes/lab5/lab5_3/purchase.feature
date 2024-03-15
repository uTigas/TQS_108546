Feature: Blazedemo WalkThrough

Background:
    Given we enter blazedemo main page

Scenario: Purchase Flight
    When I enter the main Page and Choose to go from 'San Diego' to 'Dublin'
    And I chose the first flight 
    And fill in my personal data
    Then I am presented with a page with the title 'BlazeDemo Confirmation'
