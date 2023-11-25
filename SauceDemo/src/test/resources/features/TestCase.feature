@Execution

  Feature: E2E Shopping cart flow
    Scenario: Validate E2E flow for product purchase
      Given User navigates to the Sauce Labs website
      And User logs in with valid credentials
      When User selects two products with the same price
      And User proceeds to the cart for order confirmation
      And User enters valid personal information
      Then User verifies the accuracy of entered information
      And User confirms the purchase and receives a successful transaction message