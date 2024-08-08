Feature: Content Management

  Scenario Outline: manage recipes

    When the admin  choose recipe id to delete <id>
    Then the recipe is deleted and a message is shown
    Examples:
       | id  |
       | 0 |

  Scenario Outline: manage feedbacks
    When the admin chooses a recipe to display its feedbacks by  its id <id> and FeedbackId <FeedbackId>
    Then the feedback is deleted
    Examples:
      | id  |FeedbackId|
      | 0 |0       |

