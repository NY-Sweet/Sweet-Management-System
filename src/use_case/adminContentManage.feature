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
  Scenario Outline: manage feedbacks invalid feedback id
    When the admin chooses a recipe to display its feedbacks by  its id <id> and  Invalid FeedbackId <FeedbackId>
    Then  failed message appears Invalid Feedback Id
    Examples:
      | id  |FeedbackId|
      | 0 |-1       |

  Scenario Outline: manage feedbacks invalid Recipe  id
    When the admin chooses a recipe to display its feedbacks by  Invalid  id <id> and   FeedbackId <FeedbackId>
    Then  failed message appears Invalid Recipe Id
    Examples:
      | id  |FeedbackId|
      | -1|0       |


  Scenario Outline: Invalid Recipe Id

    When the admin  enters Invalid  recipe id  <id>
    Then Message Invalid Recipe Id
    Examples:
      | id  |
      | -1|
  Scenario Outline: valid Recipe Id

    When the admin  enters valid  recipe id  <id>
    Then Message valid Recipe Id
    Examples:
      | id  |
      | 10|