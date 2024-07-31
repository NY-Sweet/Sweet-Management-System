Feature: Monitoring and Reporting

  Scenario Outline: admin enters the Admin page
    Given the  in admin page
    When the admin enters the <int1>
    Then the admin enters the Reports page
    Examples:
      | int1 |
      | 2  |

  Scenario Outline: admin doesn't enter the Reports page
    Given the admin in admin page
    When the admin enters the <int1>
    Then the admin doesn't enter the Reports page
    Examples:
      | int1 |
      | 3    |

  Scenario Outline: Show financial reports
    Given the admin is on the Reports page
    When the admin selects the financial reports option <int1>
    Then the reports will display all shops' financial details

    Examples:
      | int1 |
      | 1    |

  Scenario Outline: Show best-selling products in each store.
    Given the admin is on the Reports page
    When the admin selects the best-selling products in each store option <int1>
    Then the reports will display best-selling products in each store

    Examples:
      | int1 |
      | 2    |

  Scenario Outline: display statistics on registered users by City
    Given the admin is on the Reports page
    When the admin selects the statistics on registered users by City option <int1>
    Then the reports will display statistics on registered users by City

    Examples:
      | int1 |
      |3    |
  Scenario: back to admin page
    Given the admin in Reports page
    When the user select back "4"
    Then back to admin page

