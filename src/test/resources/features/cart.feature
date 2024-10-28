Feature: Cart Functionality for SwagLabs E-commerce Website

  As a user of the Swag Labs website
  I want to be able to select and order the items displayed
  So that I can place my orders from inventory

  Background:
    Given I am on the Swag Lab login page
    And I have logged in successfully

  Scenario Outline: Cross check the order ready to be completed
    When I select "<item1>","<item2>","<item3>"
    And I navigate to checkout overview page
    Then I should see the correct total price and a Finish button

    Examples:
      | item1                 | item2                             | item3                    |
      | Sauce Labs Backpack   | Sauce Labs Bike Light             | Sauce Labs Fleece Jacket |
      | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie     | Test.allTheThings() T-Shirt (Red) |                          |

@fixMe
  Scenario Outline: Place order successfully
    When I select "<item1>","<item2>","<item3>"
    And I submit the order with all the required details
    Then I should see order been placed successfully
    And I should see the Thank you message

    Examples:
      | item1                 | item2                             | item3                    |
      | Sauce Labs Backpack   | Sauce Labs Bike Light             | Sauce Labs Fleece Jacket |
      | Sauce Labs Bike Light | Test.allTheThings() T-Shirt (Red) | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie     | Test.allTheThings() T-Shirt (Red) |                          |

