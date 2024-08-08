Feature: Admin account manage accounts

  Scenario Outline: admin  add new supplier
    When admin add new supplier by  setting  new username <newUsername>, password <password>, city=<city>,street=<street>,home number=<homeNumber>, phone number=<phoneNumber> , email=<email>  role=<role>  shop name <Name> and employeeNumber <employeeNumber>
    Then a new supplier will be created
    Examples:
      | newUsername | password | city     |street         | homeNumber     |  phoneNumber  | email            |role|Name             |employeeNumber|
      | "Hana"     | "11"     | "Nablus" |"Nablus street"|"54G"           | "0594507933"  | "ahmad@gmail.com" |"s"  |"Abu ahmad shop"|5|

  Scenario Outline: admin  add new user
    When admin add new user by setting  new username <newUsername>, password <password>, city=<city>,street=<street>,home number=<homeNumber>, phone number=<phoneNumber> , email=<email>  role=<role>
    Then a new user will be created
    Examples:
     | newUsername | password | city     |street         | homeNumber     |  phoneNumber  | email            |role|
     | "adam"      |"54321"   | "Nablus" |"Nablus street"|"54G"           |  "0594507933" | "haya@gmail.com" |"u" |

  Scenario Outline: admin  delete account
    When  the admin set  username <newUsername> to delete this user
    Then the user deleted and a message displayed
    Examples:
   | newUsername |
   | "haya"      |

  Scenario: admin show supplier
    When the admin set option to show supplier
    Then all supplier details displayed


  Scenario: admin show users
    When the admin set option to show users
    Then all users details displayed



    


