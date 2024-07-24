Feature: Post personal dessert creations, Search, Browse, Filter

  Scenario Outline: user enters the Recipe page
    Given the user in user page
    When the user enters the <int1>
    Then the user enters the Recipe page
    Examples:
      | int1 |
      | 2    |


  Scenario Outline: user doesnt enters the Recipe page
    Given the user in user page
    When the user enters the <int1>
    Then the user doesnt enters the Recipe page
    Examples:
      | int1 |
      | 3    |

    Scenario Outline: post a new recipe
      Given the user is on the  Recipe page
      When the user select Post Recipe <int1>
      And the user <userName> fills in the recipe details including name <name>, number of ingredients <numberOfIngredients>, ingredients <ingredients>, steps <steps>
      Then the system validates the inputs
      And if the details are valid, the recipe is created successfully
      And a  message is displayed
      Examples:
       |int1 | userName | name             | numberOfIngredients | ingredients       | steps|
       | "1"| "salma" | " cake"           | "3"                 | "egge milk flour" |"mix"|


  Scenario Outline: Browse recipes
    Given the user is on the  Recipe page
    When the user select Browse Recipe <int1>
    Then Display all recipes from all users
    Examples:
      |int1 |
      |"2"  |


  Scenario Outline: Search recipes
    Given the user is on the  Recipe page
    When the user select Search Recipe <int1>
    And the user enter the <recipeName>
    Then Display all recipes match
    Examples:
      |int1 |recipeName|
      |"3"  |"cake"    |


  Scenario Outline: Search recipes
    Given the user is on the  Recipe page
    When the user select Search Recipe <int1>
    And the user enter the <recipeName>
    Then Display all recipes match
    Examples:
      |int1 |recipeName|
      |"3"  |"cake"    |


  Scenario Outline: Filter recipes based on allergies
    Given the user is on the  Recipe page
    When the user select Search Recipe <int1>
    And the user enter the <foodAllergies>
    Then Display all recipes based on allergies
    Examples:
      |int1 |foodAllergies|
      |"4"  |"milk"     |


  Scenario Outline: Filter recipes based on dietary
    Given the user is on the  Recipe page
    When the user select Search Recipe <int1>
    And the user enter the <foodDietary>
    Then Display all recipes based on dietary
    Examples:
      |int1 |foodDietary|
      |"5"  |"eggs"     |

  Scenario Outline: Feedback to recipes
    Given the user is on the Recipe page
    When the user selects Feedback Recipe option <int1>
    And the user enters the recipe ID <id> and feedback content <content>
    Then the feedback is sent successfully

    Examples:
      | int1 | id | content                  |
      | "6"    | 101 | "Delicious and easy!"    |
      |    "6" | 102 | "Needs more seasoning."  |
      | "6"    | 103 | "Loved the presentation!"|

