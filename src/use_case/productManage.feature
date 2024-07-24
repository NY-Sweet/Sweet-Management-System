Feature: add delete edit and discount

  Scenario Outline: owner enters the Product page
    Given the owner in owner page
    When the owner enters the <int1>
    Then the owner enters the Product page
    Examples:
      | int1 |
      | 1    |

  Scenario Outline: owner doesn't enter the Product page
    Given the owner in owner page
    When the owner enters the <int1>
    Then the owner doesn't enter the Product page
    Examples:
      | int1 |
      | 2    |

  Scenario Outline: View Products
    Given the owner in Product page
    When the owner enters the <option>
    Then product show
    Examples:
      | option |
      | "0"  |

  Scenario Outline: add product
    Given the owner in Product page
    When the owner enters the <option>
    And set product Id=<productId> product name =<name> quantity =<quantity>  price=<price>  cost=<cost> expiration date <int1>-<int2>-<int3> discount percentage=<discount per>
    Then the system validates the inputs
    And if the details are valid, the product is created successfully
    And a  message is displayed
    Examples:
      | option | productId|name  |quantity|price|cost|int1|int2 |int3|discount per|
      | "1"    |"1"       |"milk"|20   |10 |8 |5  |1  |2024  |10|

  Scenario Outline: edit product name
    Given the owner in Product page
    When the owner enters the <option>
    And set product Id=<productId> new product name =<name>
    Then the system validates the inputs
    And if the details are valid, the product name update successfully
    And a  message is displayed
    Examples:
      | option |productId| name|
      | "2a"    |"101"   |"milk free sugar"     |

  Scenario Outline: edit product quantity
    Given the owner in Product page
    When the owner enters the <option>
    And set product Id=<productId> new product quantity =<quantity>
    Then the system validates the inputs
    And if the details are valid, the product quantity update successfully
    And a  message is displayed
    Examples:
      | option | productId|quantity|
      | "2b"    |"101"    |"1"     |

  Scenario Outline: edit product price
    Given the owner in Product page
    When the owner enters the <option>
    And set product Id=<productId> new product price =<price>
    Then the system validates the inputs
    And if the details are valid, the product price update successfully
    And a  message is displayed
    Examples:
      | option |productId| price|
      | "2c"    |"101"   |"100" |

  Scenario Outline: edit product cost
    Given the owner in Product page
    When the owner enters the <option>
    And set product Id=<productId> new product cost =<cost>
    Then the system validates the inputs
    And if the details are valid, the product cost update successfully
    And a  message is displayed
    Examples:
      | option |productId| cost|
      | "2d"    |"101"   |"80" |

  Scenario Outline: delete product
    Given the owner in Product page
    When the owner enters the <option>
    And set product Id=<productId>
    Then the system validates the inputs
    And if the details are valid, the product delete successfully
    And a  message is displayed
    Examples:
      | option | productId|
      |"3"    |101     |
  Scenario Outline: View Discount Product
    Given the owner in Product page
    When the owner enters the <option>
    Then discount product show
    Examples:
      | option |
      | "4a"  |
  Scenario Outline: Discount rule
    Given the owner in Product page
    When the owner enters the <option>
    And set percentage =<percentage> day before expiration=<day>
    Then the system validates the inputs
    And if the details are valid, the Discount rule Applied successfully
    And a  message is displayed
    Examples:
      | option | percentage| day|
      | "4b"    |10    |8          |


