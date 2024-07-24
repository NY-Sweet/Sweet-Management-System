Feature: Admin account manage accounts

  Scenario Outline: admin enters the Admin page
    Given the  in admin page
    When the admin enters the <int1>
    Then the admin enters the account manage page
    Examples:
      | int1 |
      | 1   |

  Scenario Outline: admin doesn't enter the account manage page
    Given the admin in admin page
    When the admin enters the <int1>
    Then the admin doesn't enter the account manage page
    Examples:
      | int1 |
      | 2    |

  Scenario Outline: admin  add new owner
    Given the admin in account manage page
    When the admin set option to add new owner <int1>
    And When set new username <newUsername>, password <password>, city=<city>,street=<street>,home number=<homeNumber>, phone number=<phoneNumber> , email=<email>  role=<role>  shop name <Name> and employee number<employeeNumber>
    And if the details are valid, the system saves the new user details
    Then create succeed
    Examples:
    |int1  | newUsername | password | city     |street         | homeNumber     |  phoneNumber  | email            |role|Name|employeeNumber|
     |1 | "salma"     | "1234"   | "Nablus" |"Nablus street"|"54G"           |  "0594507933" | "salma@gmail.com" |"o"  |"NY sweet"|6     |

  Scenario Outline: admin  add new supplier
    Given the admin in account manage page
    When the admin set option to add new supplier <int1>
    And When set new username <newUsername>, password <password>, city=<city>,street=<street>,home number=<homeNumber>, phone number=<phoneNumber> , email=<email>  role=<role>  shop name <Name> and employee number<employeeNumber>
    And if the details are valid, the system saves the new user details
    Then create succeed
    Examples:
    |int1  | newUsername | password | city     |street         | homeNumber     |  phoneNumber  | email            |role|Name|employeeNumber|
      |1| "AbuAhmad"     | "11"     | "Nablus" |"Nablus street"|"54G"           | "0594507933"  | "ahmad@gmail.com" |"s"  |"Abu ahmad shop"|5|

  Scenario Outline: admin  add new user
    Given the admin in account manage page
    When the admin set option to add new user <int1>
    And When set new username <newUsername>, password <password>, city=<city>,street=<street>,home number=<homeNumber>, phone number=<phoneNumber> , email=<email>  role=<role>
    And if the details are valid, the system saves the new user details
    Then create succeed
    Examples:
   |int1   | newUsername | password | city     |street         | homeNumber     |  phoneNumber  | email            |role|
     |1 | "adam"      |"54321"   | "Nablus" |"Nablus street"|"54G"           |  "0594507933" | "haya@gmail.com" |"u" |

  Scenario Outline: admin  delete account
    Given the admin in account manage page
    When the admin set option to delete account <int1>
    And When set  username <newUsername>
    Then message succeed displayed
    Examples:
    |int1  | newUsername |
      |2| "adam"      |

  Scenario Outline: admin show supplier
    Given the admin in account manage page
    When the admin set option to show supplier <int1>
    Then all supplier details displayed
    Examples:
      |int1  |
      |3|

  Scenario Outline: admin show users
    Given the admin in account manage page
    When the admin set option to show users <int1>
    Then all users details displayed
    Examples:
      |int1  |
      |4|

  Scenario Outline: admin show owners
    Given the admin in account manage page
    When the admin set option to show owners <int1>
    Then all owners details displayed
    Examples:
      |int1  |
      |5|
    
  Scenario back to admin page
    Given the admin in account manage page
    When the user select back "6"
    Then back to admin page


