
@list_locations
Feature: Ticketer Operations

    Background: Setup
        Given im in the mainpage and the Database has some data

    Scenario: List Locations
        When I click List Locations 
        Then im presented with multiples terminal options

