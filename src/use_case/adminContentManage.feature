Feature: Content Management

  Scenario Outline: admin enters the Admin page
    Given the admin is on the admin page
    When the admin enters <int1>
    Then the admin enters the Manage content page
    Examples:
      | int1 |
      | 3    |

  Scenario Outline: admin doesn't enter the Manage content page
    Given the admin is on the admin page
    When the admin enters <int1>
    Then the admin doesn't enter the Manage content page
    Examples:
      | int1 |
      | 3    |

  Scenario Outline: manage recipes
    Given the admin is on the manage content page
    When the admin chooses option manage recipes <int1> and sets recipe id to delete <id>
    Then the recipe is deleted and a message is shown
    Examples:
      | int1 | id  |
      | 1    | 101 |

  Scenario Outline: manage feedbacks
    Given the admin is on the manage content page
    When the admin chooses option manage feedbacks <int1> and sets feedback id to delete <id>
    Then the feedback is deleted and a message is shown
    Examples:
      | int1 | id  |
      | 2    | 123 |

  Scenario: back to admin page
    Given the admin is on the account manage page
    When the user selects back "6"
    Then it goes back to the admin page