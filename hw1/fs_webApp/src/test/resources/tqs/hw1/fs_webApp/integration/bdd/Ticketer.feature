
@list_locations
Feature: Ticketer Full Stack Operations

    Background: Setup
        Given Im in the mainpage

    Scenario: List Terminals
        When I click the Terminals Button on the Main Page
        Then Im presented with multiple terminal options

