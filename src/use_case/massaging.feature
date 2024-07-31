Feature: massaging system

  Scenario Outline: Send Message success
    Given the user in Messages  page
    When user <user> set valid receiver=<receiver> content =<content>  date <int1>-<int2>-<int3> status read=<read>
    Then success message
    Examples:
    |user|receiver|content|int1|int2 |int3| read|
    |"noor"|"haya"  |"hi "     |1   |1    |2024|0    |


  Scenario Outline: Send Message failed invalid receiver
    Given the user in Messages  page
    When user <user> set invalid receiver=<receiver> content =<content> date <int1>-<int2>-<int3> status read=<read>
    Then Failed message
    Examples:
  |user |receiver|content|int1|int2 |int3| read|
   |"noor"|"haa"   |"hi "  |1   |1    |2024|0    |

  Scenario Outline: View inbox Message
    Given the user in Messages  page
    When  user <user> View inbox Message
    Then message is displayed
    Examples:
    |user|
     |"haya"|


