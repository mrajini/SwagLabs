Feature: Login Functionality for SwagLabs E-commerce Website

  As a user of the Swag Labs website
  I want to be able to log in with my account
  So that I can access my account-related features and manage my orders

  Background:
    Given I am on the Swag Lab login page

  Scenario Outline: Successful login with valid credentials
    Given I have entered a valid "<username>" and "<password>"
    When I click on the login button
    Then I should be logged in successfully and see "<expected title>"

    Examples:
      | username                | password     | expected title |
      | standard_user           | secret_sauce | Swag Labs      |
      | performance_glitch_user | secret_sauce | Swag Labs      |
      | problem_user            | secret_sauce | Swag Labs      |

  Scenario Outline: Unsuccessful login with invalid or empty credentials
    Given I have entered invalid "<username>" and "<password>"
    When I click on the login button
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username         | password     | error_message                                                             |
      | locked_out_user  | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | Invalid_username | secret       | Epic sadface: Username and password do not match any user in this service |


  Scenario: Logout successfully
    Given I have logged in successfully
    When I click on the logout button
    Then I should be redirected to the login page
