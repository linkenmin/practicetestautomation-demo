@Firefox
Feature: Firefox Test Cases
  Background:
    Given I launch the login page
  
  @FunctionalTest
  Scenario: Positive Login Test on Firefox
    When I login with username "student" and password "Password123"
    Then I should see the secure page
    When I click the logout button
    Then I should see the login page again

  @NegativeTest
  Scenario: Negative Username Test on Firefox
    When I login with username "incorrectUser" and password "Password123"
    Then I should see an error message "Your username is invalid!"

  @NegativeTest
  Scenario: Negative Password Test on Firefox
    When I login with username "student" and password "incorrectPassword"
    Then I should see an error message "Your password is invalid!"