
Feature: Login functionality of the application

  Background: for scenarios in this feature file, user is expected to be on the login page
    When user is on the login page


  Scenario: user can successfully log in to the application - positive scenario
    When user enters valid username
    And user enters valid password
    And user clicks on login button
    Then verify that user is successfully logged into the application
  @wip
  Scenario: locked out user can not log in and appropriate message is displayed
    When locked out user enters username
    And locked out user enters password
    And user clicks on login button
    Then verify that error message is displayed correctly


