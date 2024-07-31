Feature:  Manage personal accounts

  Scenario Outline: user enters the Account setting page
    Given the user in user page
    When the user enters the <int1>
    Then the user enters the Account setting page
    Examples:
      | int1 |
      | 1    |
  Scenario Outline: user doesnt enters the Account setting page
    Given the user in user page
    When the user enters the <int1>
    Then the user doesnt enters the  Account setting page
    Examples:
      | int1 |
      | 2    |

  Scenario: change user city
    Given  user is on the account settings Account_Settings
    When the user select edit city "c"
    And the user updates the "new_City" field with a new city name
    Then the system validates the new city details
    And if the details are valid, the system saves the new city
    And the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario: change user street
    Given  user is on the account settings Account_Settings
    When the user select edit street "s"
    And the user updates the "new_street" field with a new street name
    Then the system validates the new street details
    And if the details are valid, the system saves the new street
    And the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario: change user home number
    Given  user is on the account settings Account_Settings
    When the user select edit home number "h"
    And the user updates the "new_home_number" field with a new home number name
    Then the system validates the new home number details
    And if the details are valid, the system saves the new home number
    And the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario: change user phone number
    Given  user is on the account settings Account_Settings
    When the user select edit phone number "n"
    And the user updates the "new_phone_number" field with a new phone number name
    Then the system validates the new phone number details
    And if the details are valid, the system saves the new phone number
    And the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario: change user email
    Given  user is on the account settings Account_Settings
    When the user select edit email "e"
    And the user updates the "new_email" field with a new email name
    Then the system validates the new email details
    And if the details are valid, the system saves the new email
    And the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario: change user password
    Given  user is on the account settings Account_Settings
    When the user select edit password "p"
    And the user enters old password  "old Password" new password "New Password" and confirm password "Confirm Password"
    Then the system validates the old password
    And new password match the confirm password
    And the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario: back to user page
    Given  user is on the account settings Account_Settings
    When the user select back "b"
    Then back to user page



