Feature:  Provide feedback on purchased products


  Scenario Outline: user enters the Feedback page
Given the  in user page
When the user enters the <int1>
Then the user enters the Feedback page
    And orders with detail display
Examples:
| int1 |
| 5   |

Scenario Outline: user doesn't enter the Feedback page
Given the user in user page
When the user enters the <int1>
Then the user doesn't enter the Feedback page
Examples:
| int1 |
| 6    |


  Scenario Outline: User provides feedback to a product
    Given the user is on the feedback page
    When the user enters  product ID <id>, and feedback content <content>
    Then the feedback is sent and a confirmation message appears

    Examples:
      | id | content              |
      | 1  | "Great product!"     |
      | 2  | "Could be improved." |
      | 3  | "Excellent quality!" |


