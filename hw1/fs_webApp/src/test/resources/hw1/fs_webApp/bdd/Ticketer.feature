
@list_locations
Feature: Ticketer Operations

    Background: Setup
        Given im in the mainpage and the Database has some data

    Scenario: List Terminals
        When I click the Terminals Button on the Main Page
        Then im presented with multiple terminal options

