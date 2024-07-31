Feature: Monitor sales and profits and Identify best-selling products.

  Scenario Outline: owner enters the Reports page
    Given the owner in owner page
    When the owner enters the option to Reports page <int1>
    Then the owner enters the Reports page
    Examples:
      | int1 |
      | 2   |

  Scenario Outline: owner doesn't enter the Reports page
    Given the owner in owner page
    When the owner enters the option to Reports page <int1>
    Then the owner doesn't enter the Reports page
    Examples:
      | int1 |
      | 3    |

  Scenario Outline: View Daily Sales and Profits
    Given the owner in Reports page
    When the owner <owner> enters the option to view Daily Sales and Profits <option>
    And set date <int1>-<int2>-<int3>
    Then  View Daily Sales and Profits successfully

    Examples:
    |owner  | option | int1|int2 |int3|
    |"noor"  | "1"    |27      |7|2024    |

  Scenario Outline: View Monthly Sales and Profits
    Given the owner in Reports page
    When the owner <owner> enters the option to View Monthly Sales and Profits <option>
    And set date month and year <int1>-<int2>
    Then  View Monthly Sales and Profits successfully

    Examples:
      |owner   | option | int1|int2 |
      |"noor"   | "2"    |7       |2024|

  Scenario Outline: Show Annual financial reports
    Given the owner in Reports page
    When the owner <owner> selects the financial reports set year <year>
    Then the owner financial reports will display all shops' financial details

    Examples:
      |owner| year |
      |"noor"| 2024   |


  Scenario Outline: View the Best Selling Products
    Given the owner in Reports page
    When the owner <owner> enters the option to View the Best Selling Products <option>
    Then View the Best Selling Products successfully

    Examples:
      |owner   | option |
      |"noor"   | "3"    |

  Scenario Outline: Exit
    Given the owner in Reports page
    When the owner enters the option to exit reports page  <option>
    Then exit owner reports page and go back to owner page
    Examples:
      | option |
      | "4"   |

  Scenario Outline: invalid option
    Given the owner in Reports page
    When the owner enters an invalid option in reports page <option>
    Then invalid option in report page and  message is displayed
    Examples:
      | option |
      |"9"   |


