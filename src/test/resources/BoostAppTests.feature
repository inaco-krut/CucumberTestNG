Feature: Create a new challenge

  @createEditDeleteChallenge
  Scenario: As admin I should be able to creates, edit and delete a challenge
    Given The admin is on the landing page
    Given The admin press challenge button
    Given The admin press create new challenge button
    Given The admin fills out challenge information
    When Admin then press Save button
    And Admin edit the challenge
    Then Admin delete the challenge


