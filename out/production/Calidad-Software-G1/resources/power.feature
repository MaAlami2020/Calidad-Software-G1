Feature: User is able to modify the power of its character

  Scenario: modify the value of the power of a character using a mock
    Given an user character's with a power value defined
    When he registers in the app and creates a character giving it a power value
    Then the user can modify its value