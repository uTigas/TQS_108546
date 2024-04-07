
@list_locations
Feature: Ticketer Operations

    Background: Setup
        Given Im in the mainpage

    Scenario: List Terminals
        When I click the Terminals Button on the Main Page
        Then Im presented with multiple terminal options

