Feature:  Provide feedback on purchased products

  Scenario Outline: User provides feedback to a product
    When the user enters  product ID <id>, and feedback content <content>
    Then the feedback is sent and a confirmation message appears

    Examples:
      | id | content                  |
      | "101"  | "Great product!"     |
      | "101"  | "Could be improved." |
      | "101"  | "Excellent quality!" |

  Scenario Outline: User provides feedback to a product
    When the user enters  product ID <id> and this product is not asigned , and feedback content <content>
    Then a failed message appears

    Examples:
      | id | content                  |
      | "-1"  | "Great product!"     |


