Feature: User is able to challenge to another

  Scenario: send a challenge to another user using a fake server
    Given that defiant user wants to challenge to another a defied user
    When he creates a challenge in which he defines the defied and bets an amount of gold
    Then a new challenge is saved in a fake database of challenges
#feature in which is defined a scenario which is inserting a challenge in a fake server