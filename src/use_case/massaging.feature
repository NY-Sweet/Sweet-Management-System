Feature: massaging system

  Scenario Outline: owner enters the Message page
    Given the  in owner page
    When the owner enters the <int1>
    Then the owner enters the Message page
    Examples:
      | int1 |
      | 3   |

  Scenario Outline: owner doesn't enter the Message page
    Given the owner in owner page
    When the owner enters the <int1>
    Then the owner doesn't enter the Message page
    Examples:
      | int1 |
      | 4    |



  Scenario Outline: Send Message success
    Given the owner in Messages  page
    When set option=<option> valid receiver=<receiver> content =<content>  date <int1>-<int2>-<int3> status read=<read>
    Then success message
    Examples:
    |option|receiver|content|int1|int2 |int3| read|
    |1     |"haya"  |"hi "     |1   |1    |2024|0    |


  Scenario Outline: Send Message failed invalid receiver
    Given the owner in Messages  page
    When set option=<option> invalid receiver=<receiver> content =<content> date <int1>-<int2>-<int3> status read=<read>
    Then Failed message
    Examples:
      |option|receiver|content|int1|int2 |int3| read|
      |1     |"haa"   |"hi "  |1   |1    |2024|0    |

  Scenario Outline: View inbox Message and reply
    Given the owner in Messages  page
    When set option=<option> set  read status=<read> reply=<reply>
    Then if reply 1 send message
     And success message
    Examples:
      |option| read|reply|
      |2     |1    |1    |



  Scenario Outline: user enters the Message page
    Given the  in user page
    When the user enters the <int1>
    Then the user enters the Message page
    Examples:
      | int1 |
      | 4   |

  Scenario Outline: user doesn't enter the Message page
    Given the user in user page
    When the user enters the <int1>
    Then the user doesn't enter the Message page
    Examples:
      | int1 |
      | 5    |



  Scenario Outline: Send Message success
    Given the user in Messages  page
    When set option=<option> valid receiver=<receiver> content =<content>  date <int1>-<int2>-<int3> status read=<read>
    Then success message
    Examples:
      |option|receiver|content|int1|int2 |int3| read|
      |1     |"haya"  |"hi "     |1   |1    |2024|0    |


  Scenario Outline: Send Message failed invalid receiver
    Given the user in Messages  page
    When set option=<option> invalid receiver=<receiver> content =<content> date <int1>-<int2>-<int3> status read=<read>
    Then Failed message
    Examples:
      |option|receiver|content|int1|int2 |int3| read|
      |1     |"haa"   |"hi "  |1   |1    |2024|0    |

  Scenario Outline: View inbox Message and reply
    Given the user in Messages  page
    When set option=<option> set  read status=<read> reply=<reply>
    Then if reply 1 send message
    And success message
    Examples:
      |option| read|reply|
      |2     |1    |1    |
