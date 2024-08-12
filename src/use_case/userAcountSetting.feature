Feature:  Manage personal accounts

  Scenario: change user city
    When the user select edit city "c"
    And the user updates the "new_City" field with a new city name
    Then the system saves the new city

  Scenario: change user street

    When the user select edit street "s"
    And the user updates the "new_street" field with a new street name
    Then  the system saves the new street


  Scenario: change user home number

    When the user select edit home number "h"
    And the user updates the "new_home_number" field with a new home number name
    Then  the system saves the new home number


  Scenario: change user phone number

    When the user select edit phone number "n"
    And the user updates the "new_phone_number" field with a new phone number name
    Then the system saves the new phone number


  Scenario Outline: change user email
    When the user select edit email "e"
    And the user updates the "new_email" field with a new email name
    Then the system saves the new email
    Examples:
      |"new_email"|
      |"yaradaraghmeh056@gmail.com"|

  Scenario Outline: change user password
    When the user select edit password "p"
    And the user enters old password  "old Password" new password "New Password" and confirm password "Confirm Password"
    Then the system validates the inputs and sets the new password
    Examples:
      | "old Password" | "New Password" | "Confirm Password" |
      | "123456789"    | "123"          | "123"              |