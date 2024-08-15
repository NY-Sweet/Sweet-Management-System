Feature: Post personal dessert creations, Search, Browse, Filter

  Scenario Outline: post a new recipe
    When  the user <userName> fills in the recipe details including name <name>, number of ingredients <numberOfIngredients>, ingredients <ingredients>, steps <steps>
    Then the system validates the inputs
    And if the details are valid, the recipe is created successfully
    And a  message is displayed
    Examples:
      | userName | name             | numberOfIngredients | ingredients       | steps|
      | "haya"   | "Pancake"          | 3                | "egge milk flour" |"mix"|


  Scenario: Browse recipes
    When the user select Browse Recipe
    Then Display all recipes from all users


  Scenario Outline: Search recipes
    When  the user enter the <recipeName>
    Then Display all recipes match
    Examples:
      |recipeName|
      |"cake"    |


  Scenario Outline: Search recipes
    When the user enter the <recipeName>
    Then Display all recipes match
    Examples:
      |recipeName|
      |"cake"    |


  Scenario Outline: Filter recipes based on allergies
    When the user enter the <foodAllergies>
    Then Display all recipes based on allergies
    Examples:
      |foodAllergies|
      |"milk"     |



  Scenario Outline: Filter recipes based on dietary

    When the user enter the <foodDietary>
    Then Display all recipes based on dietary
    Examples:
      |foodDietary|
      |"eggs"     |

  Scenario Outline: Filter recipes based on dietary and not founding any suitable

    When the user enter the <foodDietary> and not found any for him
    Then Print Nothing found suitable for you
    Examples:
      |foodDietary|
      |"eggee"     |

  Scenario Outline: Feedback to recipes
    When the user enters the recipe Name <Name> and feedback content <content>
    Then the feedback is sent successfully

    Examples:
      | Name | content                   |
      | "cake" | "Delicious and easy!"   |


