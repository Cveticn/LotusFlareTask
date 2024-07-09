Feature: user is able to add items to the shopping cart

  Background: for the scenarios in this feature file, use is expected to be logged in to the application
    When user is successfully logged in to the application


  Scenario: user is able to add items to the cart
    When user adds and item from the list to the cart
    Then verify that cart badge is updated correctly
    And user open another item's details page
    And user adds the item to the cart
    And user opens the cart
    Then verify that the correct items are present