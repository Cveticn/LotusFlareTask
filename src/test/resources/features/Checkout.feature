Feature: user is able to successfully complete the order

  Background: for the scenarios in this feature file, use is expected to be logged in to the application
    When user is successfully logged in to the application

     @test2
    Scenario: user successfully completes the order
      When user adds first item to the cart
      And user adds second item to the cart
      And user clicks on shopping cart
      And user clicks checkout button
      And user enters first name
      And user enters last name
      And user enters postal code
      And user clicks on continue button
      And user clicks on finish button
      Then verify that order is completed successfully and message is displayed correctly

  @test5
      Scenario: prices are adding up correctly at the checkout
        When user adds first item to the cart
        And user adds second item to the cart
        And user clicks on shopping cart
        And user clicks checkout button
        And user enters first name
        And user enters last name
        And user enters postal code
        And user clicks on continue button
        Then verify that prices of the items are added up correctly
