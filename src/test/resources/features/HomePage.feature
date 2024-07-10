Feature: photos are displayed to the user after he/she is logged in

  Background: for the scenarios in this feature file, use is expected to be logged in to the application
    When user is successfully logged in to the application

    @wip @test6
    Scenario: images are displayed to the user once he/she is logged in to the application
      Then verify that images are displayed to the user