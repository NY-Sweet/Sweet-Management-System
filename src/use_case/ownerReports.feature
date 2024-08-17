Feature: Monitor sales and profits and Identify best-selling products.


  Scenario Outline: View Daily Sales and Profits
    Given the owner in Reports page
    When the owner <owner> enters the option to view Daily Sales and Profits
    And set date <int1>-<int2>-<int3>
    Then  View Daily Sales and Profits successfully

    Examples:
    |owner   | int1|int2 |int3|
    |"noor"      |9      |8|2024    |

  Scenario Outline: View Monthly Sales and Profits
    Given the owner in Reports page
    When the owner <owner> enters the option to View Monthly Sales and Profits
    And set date month and year <int1>-<int2>
    Then  View Monthly Sales and Profits successfully

    Examples:
      |owner    | int1|int2 |
      |"noor"      |8       |2024|

  Scenario Outline: Show Annual financial reports
    Given the owner in Reports page
    When the owner <owner> selects the financial reports set year <year>
    Then the owner financial reports will display all shops' financial details

    Examples:
      |owner| year |
      |"noor"| 2024   |


  Scenario Outline: View the Best Selling Products
    Given the owner in Reports page
    When the owner <owner> enters the option to View the Best Selling Products
    Then View the Best Selling Products successfully

    Examples:
      |owner   |
      |"noor"   |



