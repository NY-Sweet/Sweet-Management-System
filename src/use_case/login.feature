Feature: log in user and sign up to user
  Scenario: valid login
    Given I am not in sweet management system
    When set username "haya" and password "123456"
    Then login succeed


  Scenario: invalid user name
    Given I am not in sweet management system
    When set invalid username "hayaw" and password "123"
    Then login failed


  Scenario Outline: invalid password
    Given I am not in sweet management system
    When set valid username <user_name> and invalid password <password>
    Then login failed
    Examples:
      | user_name | password    |
      | "haya"    | "12349" |
      | "naa"    | "Forget" |
      | "nay"    | "1234" |
      | "haya"    | "Forget" |



  Scenario Outline: blank user name
    Given I am not in sweet management system
    When set empty username <user_name> and valid password <password>
    Then login failed
    Examples:
      | user_name | password   |
      | ""        | "1234" |
      | "sara"        | "" |
      | "sara"        | "1234" |
      | ""        | "" |

  Scenario Outline: blank password
    Given I am not in sweet management system
    When set valid username <user_name> and empty password <password>
    Then login failed
    Examples:
      | user_name | password |
      | "haya"    | ""   |
      | ""        | "1234" |
      | ""        | "" |
      | "haya"        | "1234" |


  Scenario Outline: User Forgot Password
    Given I am not in sweet management system
    When set valid username <user_name> and  password <password>
    Then take new password <newPassword>
    Examples:
      | user_name | password     | newPassword |
      | "haya"    | "Forget" | "12345" |
      | "haa"    | "Forget" | "12345" |
      | "haya"    | "Fort" | "12345" |

  Scenario Outline: User needs to Create Account
    Given I am not in sweet management system
    And I don't have an account
    When set new username <newUsername>, password <password>, city=<city>,street=<street>,home number=<homeNumber>, phone number=<phoneNumber> , email=<email> and role=<role>
    And if the details are valid, the system saves the new user details
    Then user createed succeed
    Examples:
      | newUsername | password | city     |street         | homeNumber     |  phoneNumber  | email            |role|
      | "adam"      |"54321"   | "Nablus" |"Nablus street"|"54G"           |  "0594507933" | "haya@gmail.com" |"u" |


  Scenario Outline: Owner needs to Create Account
    Given I am not in sweet management system
    And I don't have an account
    When set new username <newUsername>, password <password>, city=<city>,street=<street>,home number=<homeNumber>, phone number=<phoneNumber> , email=<email>  role=<role>  shop name <Name> and employee number <employeeNumber>
    And if the details are valid, the system saves the new user details
    Then owner created succeed
    Examples:
      | newUsername | password | city     |street         | homeNumber     |  phoneNumber  | email            |role|Name|employeeNumber|
      | "lama"     | "1234"   | "Nablus" |"Nablus street"|"54G"           |  "0594507933" | "salma@gmail.com" |"o"  |"NY sweet"|23     |
      | "AbuAhmad"     | "11"     | "Nablus" |"Nablus street"|"54G"           | "0594507933"  | "ahmad@gmail.com" |"s"  |"Abu ahmad shop"|12|


