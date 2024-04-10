
@list_locations
Feature: Ticketer Full Stack Operations

    Background: Setup
        Given Im in the main page

    Scenario: List Terminals
        When I click Look For Adventures
        Then Im presented with multiple terminal options

