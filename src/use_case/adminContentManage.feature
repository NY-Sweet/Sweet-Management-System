Feature:  Content Management
  
  Scenario Outline: admin enters the Admin page
    Given the  in admin page
    When the admin enters the <int1>
    Then the admin enters the Manage content page
    Examples:
      | int1 |
      | 3  |

  Scenario Outline: admin doesn't enter the Manage content page
    Given the admin in admin page
    When the admin enters the <int1>
    Then the admin doesn't enter the Manage content page
    Examples:
      | int1 |
      | 3    |


  Scenario Outline: manage recipes
    Given the admin in manage content page
    When the admin choose option manage recipes <int1> set recipe id to delete <id>
    Then the recipe is deleted and a  message is shown
    Examples:
      | int1 |
      | 1    |
  Scenario Outline: manage feedbacks
    Given the admin in manage content page
    When the admin choose option manage feedbacks <int1> set feedback id to delete <id>
    Then the feedback is deleted and a  message is shown

    Examples:
      | int1 |
      | 2    |


  Scenario back to admin page
    Given the admin in account manage page
    When the user select back "3"
    Then back to admin page
