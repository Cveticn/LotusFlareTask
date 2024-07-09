
Feature: Login functionality of the application

  Background: for scenarios in this feature file, user is expected to be on the login page
    When user is on the login page


  Scenario: user can successfully log in to the application - positive scenario
    When user enters valid username
    And user enters valid password
    And user clicks on login button
    Then verify that user is successfully logged into the application

  Scenario: locked out user can not log in and appropriate error message is displayed
    When locked out user enters username
    And locked out user enters password
    And user clicks on login button
    Then verify that error message is displayed correctly


  Scenario: user can not login without username and appropriate error message is displayed
    When user enters valid password
    And user clicks on login button
    Then verify that username required error message is displayed


  Scenario: user can not login without password and appropriate error message is displayed
    When user enters valid username
    And user clicks on login button
    Then verify that password required error message is displayed


    Scenario: user can not login with invalid credentials
      When user enters invalid username
      And user enters invalid password
      And user clicks on login button
      Then verify that username and password do not match error message is displayed


      Scenario: user can use keyboard keys to navigate through login page
        When user enters valid username
        And user clicks tab on keyboard
        And user enters valid password
        And user clicks enter on keyboard
        Then verify that user is successfully logged into the application






