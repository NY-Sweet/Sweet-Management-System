Feature: Monitoring and Reporting


  Scenario Outline: Show financial reports
    Given the admin "admin" is on the Reports page
    When the admin selects the financial reports set year <year>
    Then the reports will display all shops' financial details

    Examples:
      | year |
      | 2024   |





  Scenario: Show best-selling products in each store.
    Given the admin "admin" is on the Reports page
    When the admin selects the best-selling products in each store
    Then the reports will display best-selling products in each store



  Scenario: display statistics on registered users by City
    Given the admin "admin" is on the Reports page
    When the admin selects the statistics on registered users by City
    Then the reports will display statistics on registered users by City


