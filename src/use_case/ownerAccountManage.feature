Feature:  Manage personal accounts

  Scenario Outline: owner enters the Account setting page
    Given the owner in owner page
    When the owner enters the <int1>
    Then the owner enters the Account setting page
    Examples:
      | int1 |
      | 5    |
  Scenario Outline: owner doesnt enters the Account setting page
    Given the owner in owner page
    When the owner enters the <int1>
    Then the owner doesnt enters the  Account setting page
    Examples:
      | int1 |
      | 6    |

  Scenario change owner city
    Given  owner is on the account settings Account_Settings
    When the owner select edit city "c"
    And the owner updates the "new_City" field with a new city name
    Then  the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario change owner street
    Given  owner is on the account settings Account_Settings
    When the owner select edit street "s"
    And the owner updates the "new_street" field with a new street name
    Then  the system displays a confirmation message indicating that the address has been updated successfully
    And back to Account Settings

  Scenario change owner home number
    Given  owner is on the account settings Account_Settings
    When the owner select edit home number "h"
    And the owner updates the "new_home_number" field with a new home number name
    Then  the system displays a confirmation message indicating that the home number has been updated successfully
    And back to Account Settings

  Scenario change owner phone number
    Given  owner is on the account settings Account_Settings
    When the owner select edit phone number "n"
    And the owner updates the "new_phone_number" field with a new phone number name
    Then the system displays a confirmation message indicating that the phone number has been updated successfully
    And back to Account Settings

  Scenario change owner email
    Given  owner is on the account settings Account_Settings
    When the owner select edit email "e"
    And the owner updates the "new_email" field with a new email name
    Then the system validates the new email details
    And if the details are valid, the system saves the new email
    And the system displays a confirmation message indicating that the email has been updated successfully
    And back to Account Settings

  Scenario change owner password
    Given  owner is on the account settings Account_Settings
    When the owner select edit password "p"
    And the owner enters old password  "old Password" new password "New Password" and confirm password "Confirm Password"
    Then the system validates the old password
    And new password match the confirm password
    And the system displays a confirmation message indicating that the  password has been updated successfully
    And back to Account Settings

  Scenario change employee number
    Given  owner is on the account settings Account_Settings
    When the owner select edit employee number "e"
    And the owner updates the "new employee number" field with a new employee number
    Then  the system displays a confirmation message indicating that the new employee number has been updated successfully
    And back to Account Settings

  Scenario back to owner page
    Given  owner is on the account settings Account_Settings
    When the owner select back "b"
    Then back to owner page



