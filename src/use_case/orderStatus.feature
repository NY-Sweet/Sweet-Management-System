Feature: Process and track orders [status].
  Scenario Outline: user enters the track orders page
    Given the  in user page
    When the user enters the <int1>
    Then the user enters the track orders
    Examples:
      | int1 |
      | 4  |

  Scenario Outline: user doesn't enter the track orders page
    Given the user in user page
    When the user enters the <int1>
    Then the user doesn't enter the track orders
    Examples:
      | int1 |
      | 5    |


  Scenario: Owner updates order status pending to shipped
    Given I'm the owner in track order page
    When the owner choose option "1" change pending states to "shipped"
    Then the order status should be updated and email sent

  Scenario: Owner updates order status pending to rejected
    Given I'm the owner in track order page
    When the owner choose option "1" change pending states to "rejected"
    Then the order status should be updated and email sent


  Scenario: Owner updates order status shipped to delivered
    Given I'm the owner in track order page
    When the owner choose option "2" change shipped states "delivered"
    Then the order status should be updated and email sent

  Scenario: Show delivered orders
    Given I'm the owner on the track order page
    When the owner chooses option "3"
    Then the delivered orders should be displayed
