Feature: Inventory Functionality for SwagLabs E-commerce Website

  As a user of the Swag Labs website
  I want to be able to view and order the items displayed
  So that I can place and manage my orders from inventory

  Background:
    Given I am on the Swag Lab login page
    And I have logged in successfully

    Scenario: View the details of the item selected
      Given I am on the Swag Lab inventory page
      When I click on the item image
      Then I should be navigated to item details page
      And I should see enabled "Back" and "ADD TO CART" buttons


      Scenario Outline: Add items to shopping basket
        When I select "<item1>","<item2>","<item3>"
        Then I should see "<item1>","<item2>","<item3>" in basket
    Examples:
      | item1                   | item2                             | item3                    |
      | Sauce Labs Backpack     | Sauce Labs Bike Light             | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie       | Test.allTheThings() T-Shirt (Red) |                          |
      | Sauce Labs Bolt T-Shirt |                                   |                          |
