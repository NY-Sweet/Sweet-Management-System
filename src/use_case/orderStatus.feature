Feature: Process and track orders [status].

  Scenario Outline: Owner updates order status pending to shipped
    Given I'm the owner in track order page
    When the owner <owner> choose option  change pending set id <orderId> and states <state>
    Then the order status should be updated and email sent
    Examples:
   |owner |orderId|state|
    |"noor"|"order001"  |"shipped"|
    |"noor"|"order001"  |"rejected"       |



  Scenario Outline: Owner updates order status shipped to delivered
    Given I'm the owner in track order page
    When the owner <owner> choose option change shipped set id <orderId> and states <state>
    Then the order status should be updated and email sent
    Examples:
      |owner |orderId|state|
      |"noor"|"order001"  |"delivered"|


  Scenario Outline: Show delivered orders
    Given  I'm the owner in track order page
    When the owner <owner> chooses option Show delivered orders
    Then the delivered orders should be displayed
    Examples:
      |owner|
      |"noor" |
