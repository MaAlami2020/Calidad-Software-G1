Feature: User is able to specify the type of a minion for its character

  Scenario: specify the type of minion for a character using a stub object
    Given an user who is registered in the app with a character created
    When he creates a minion for a character
    Then the user can defines the type of minion of the character