@Test
Feature: Reqres End Point
  As a user I want check end to end testing this application

  Background:
    Given I am on home page of the application

  Scenario: I want to create a user to the application
    When I send POST request to the the application using a valid payload to create a User
    And  I get status code 201
    Then I fetch the ID of newly created user

  Scenario: I want to read the new created user from the application
    When I send GET request to the application to read newly created user
    And  I get status code 200
    Then I verify in the response if it has newly created user name

  Scenario: I want to update the new created user using PUT from the application
    When I send PUT request to the application to update newly created user
    And  I get status code 200
    Then I verify in the response if it has newly created user is updated

  Scenario: I want to update the new created user using PATCH from the application
    When I send PATCH request to the application to update newly created user
    And  I get status code 200
    Then I verify in the response if it has newly created user is updated

  Scenario: I want to delete the new created user from the application
    When I send DELETE request to the application to delete newly created user
    And  I get status code 204
    Then I verify if newly created user is deleted

  Scenario: I want to read all users from the application and verify some items from response
    When I send GET request to the the application to read all users
    Then I get status code 200
    And I verify if page is 2
    And I verify if per_page is 6
    And I verify if second data's id is 8
    And I verify if forth data's first_name is Byron
    And I verify if list of data is 6
    And I verify if sixth data's avatar is "https://reqres.in/img/faces/12-image.jpg"
    And I verify if support.url is "https://reqres.in/#support-heading"
    And I verify if support.text is "To keep ReqRes free, contributions towards server costs are appreciated!"


