Feature: Account Settings

  Scenario Outline: Owner updates account settings
    Given owner "<owner>" is on the account settings Account_Settings
    When the owner selects edit <field> and updates the field with a new <new_value>
    Then the system displays a message indicating that the <field> has been updated successfully

    Examples:
      | owner | field            | new_value                |
      | noor  | city             | "Qalqilya"          |
      | noor  | street           | "Nablus-street"        |
      | noor  | home number      | "45G"        |
      | noor  | phone number     | "0593224565"       |
      | noor  | email              | "noor@gmail.com"  |
      | noor  | employee number    | "12345"                  |

  Scenario: change owner password
    Given  owner "noor" is on the account settings Account_Settings
    When the owner select edit password and enters old password  "123456" new password "12333" and confirm password "12333"
    Then the system displays a message indicating that the  password has been updated successfully

  Scenario: failed to change owner password incorrect old password
    Given  owner "noor" is on the account settings Account_Settings
    When the owner select edit password and enters incorrect old password  "9999" new password "12333" and confirm password "12333"
    Then the system displays a message indicating that the old password wrong

  Scenario: failed to change owner password mismatch password
    Given  owner "noor" is on the account settings Account_Settings
    When the owner select edit password and enters old password  "123456" new password "12333" and mismatch confirm password "99999"
    Then the system displays a message indicating that the  password mismatch
    Scenario: show owner account information
      Given  owner "noor" is on the account settings Account_Settings
      When the owner select account information
      Then display owner information