Feature: Monitor sales and profits and Identify best-selling products.

  Scenario Outline: owner enters the Reports page
    Given the owner in owner page
    When the owner enters the <int1>
    Then the owner enters the Product page
    Examples:
      | int1 |
      | 2   |

  Scenario Outline: owner doesn't enter the Reports page
    Given the owner in owner page
    When the owner enters the <int1>
    Then the owner doesn't enter the Product page
    Examples:
      | int1 |
      | 3    |

  Scenario Outline: View Daily Sales and Profits
    Given the owner in Reports page
    When the owner enters the <option>
    And set date <int1>-<int2>-<int3>
    Then the system validates the inputs
    And if the details are valid, View Daily Sales and Profits successfully

    Examples:
      | option | int1|int2 |int3|
      | "1"    |1       |1|2024    |

  Scenario Outline: View Monthly Sales and Profits
    Given the owner in Reports page
    When the owner enters the <option>
    And set date <int1>-<int2>
    Then the system validates the inputs
    And if the details are valid, View Daily Sales and Profits successfully

    Examples:
      | option | int1|int2 |
      | "1"    |1       |2024|

  Scenario Outline: View the Best Selling Products
    Given the owner in Reports page
    When the owner enters the <option>
    Then View the Best Selling Products successfully

    Examples:
      | option |
      | "3"    |

