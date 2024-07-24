Feature: Purchase desserts directly from store owners

  Scenario Outline: user enters the Purchase desserts
    Given the user in user page
    When the user enters the <int1>
    Then the user enters the Purchase  desserts page
    And the shop list display
    Examples:
      | int1 |
      | 3    |


  Scenario Outline: user doesnt enters the Purchase desserts page
    Given the user in user page
    When the user enters the <int1>
    Then the user doesnt enters the Purchase desserts page
    Examples:
      | int1 |
      | 4    |

  Scenario Outline: User enters the shop name
    Given the user is on the Purchase Desserts page
    When the user enters the shop name <Name>
    Then the shop's products with details should be displayed

    Examples:
      | Name       |
      | "NY Sweet" |

  Scenario Outline: User chooses products
    Given the user is on the Purchase Desserts page
    When the user enters the product ID <id>, the quantity <quantity>, and indicates whether to order more products <order_more>
    Then if the order ends, display the total and send an email

    Examples:
      | id | quantity | order_more |
      | 1  | 2        | no         |
      | 2  | 3        | yes        |
      | 3  | 1        | no         |