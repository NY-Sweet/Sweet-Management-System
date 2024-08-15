Feature: Purchase desserts directly from store owners.

  Scenario Outline: Purchase desserts directly from store owners
    Given I am a user <user> on the Purchase desserts page
    When I set valid shop name <shopName>
    And I select products with ids <productIds> and quantities <quantities>
    Then the order is successfully placed

    Examples:
      | user | shopName | productIds | quantities |
      | "sara" | "sweetee" | "101,102"  | "2,3"      |

  Scenario Outline: Purchase desserts with insufficient stock
    Given I am a user <user> on the Purchase desserts page
    When I set valid shop name <shopName>
    And I select products with ids <productIds> and unavailable quantities <quantities>
    Then an error message the order is rejected due to insufficient stock

    Examples:
      | user | shopName | productIds | quantities |
      | "sara" | "sweetee" | "101,102"  | "2,300"    |

  Scenario Outline:failed Purchase desserts directly from store owners Invalid shop ID
    Given I am a user <user> on the Purchase desserts page
    When I set invalid shop name <shopName>
    Then an error message Invalid shop Name is displayed

    Examples:
     |user | shopName |
     |"sara"   | "999"    |
     |"sara"    | "lll"     |

  Scenario Outline:failed Purchase desserts directly from store owners Invalid product ID
    Given I am a user <user> on the Purchase desserts page
    When I set valid shop name <shopName>
    And I select invalid products with ids <productIds> and quantities <quantities>
    Then an error message Invalid product ID is displayed

    Examples:
      |user | shopName | productIds    | quantities |
      |"sara"   | "sweetee"     | "999,102"     | "2,3"      |
      |"sara"  | "sweetee"     | "-1,102"      | "2,3"      |
