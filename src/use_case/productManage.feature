Feature: add delete edit and discount



  Scenario Outline: View Products
    Given the owner in Product page
    When the <owner> enters option to show products
    Then product show
    Examples:
      |owner|
      |"noor"|

  Scenario Outline: add product
    Given the owner in Product page
    When the owner <owner> enters option to add products
    And set product Id=<productId> product name =<name> quantity =<quantity>  price=<price>  cost=<cost> expiration date <int1>-<int2>-<int3> discount percentage=<discount per>
    Then  the product is created successfully and a  message is displayed
    Examples:
     |owner  | productId|name  |quantity|price|cost|int1|int2 |int3|discount per|
      |"noor"    |"103"       |"milk"|20   |10 |8 |5  |1  |2024  |10|

  Scenario Outline: failed to add product
    Given the owner in Product page
    When the owner <owner> enters option to add products
    And set invalid product Id=<productId> product name =<name> quantity =<quantity>  price=<price>  cost=<cost> expiration date <int1>-<int2>-<int3> discount percentage=<discount per>
    Then   message is displayed the product is already added
    Examples:
      |owner  | productId|name  |quantity|price|cost|int1|int2 |int3|discount per|
      |"noor"    |"101"       |"milk"|20   |10.5 |8.5 |5  |1  |2024  |10.2|

  Scenario Outline: edit product name
    Given the owner in Product page
    When the owner <owner> enters option to edit product name
    And set product Id=<productId> new product name =<name>
    Then the product name update successfully message is displayed
    Examples:
      |owner  |productId| name|
      |"noor"     |"101"   |"milk free sugar"     |

  Scenario Outline: failed to edit product name
    Given the owner in Product page
    When the owner <owner> enters option to edit product name
    And set product invalid Id=<productId> new product name =<name>
    Then failed to edit the product name and message is displayed
    Examples:
      |owner    |productId| name|
      |"noor"     |"000"   |"milk free sugar"     |

  Scenario Outline: edit product quantity
    Given the owner in Product page
    When the owner <owner> enters the option to edit product quantity
    And set product Id=<productId> new product quantity =<quantity>
    Then  the product quantity update successfully and a message is displayed
    Examples:
      |owner    | productId|quantity|
      |"noor"     |"101"    |10     |

  Scenario Outline:failed to edit product quantity
    Given the owner in Product page
    When the owner <owner> enters the option to edit product quantity
    And set invalid product Id=<productId> new product quantity =<quantity>
    Then  failed to edit the product quantity  and a message is displayed
    Examples:
      |owner  | productId|quantity|
      |"noor"    |"000"    |1     |

  Scenario Outline: edit product price
    Given the owner in Product page
    When the owner <owner> enters the option to edit the product price
    And set product Id=<productId> new product price =<price>
    Then  the product price update successfully and a message is displayed
    Examples:
      |owner   |productId| price|
      |"noor"    |"101"   |120.2 |

  Scenario Outline: failed to edit product price
    Given the owner in Product page
    When the owner <owner> enters the option to edit the product price
    And set invalid product Id=<productId> new product price =<price>
    Then  failed to edit the product price and a message is displayed
    Examples:
      |owner  |productId| price|
      |"noor"    |"000"   |100.5|

  Scenario Outline: edit product expiration date
    Given the owner in Product page
    When the owner <owner> enters the option to edit product  expiration date
    And set product Id=<productId> new product expiration date =<day>-<month>-<year>
    Then the product expiration date update successfully And a  message is displayed
    Examples:
      |owner    |productId|  day|month|year|
      |"noor"      |"101"   |10|10     |2024|

  Scenario Outline: failed to edit product expiration date
    Given the owner in Product page
    When the owner <owner> enters the option to edit product  expiration date
    And set invalid product Id=<productId> new product expiration date =<day>-<month>-<year>
    Then failed to edit the product expiration date  And a  message is displayed
    Examples:
      |owner   |productId| day|month|year|
      |"noor"    |"000"   |10|10     |2024|

  Scenario Outline: edit product cost
    Given the owner in Product page
    When the owner <owner> enters the option to edit product cost
    And set product Id=<productId> new product cost =<cost>
    Then the product cost update successfully And a  message is displayed
    Examples:
      |owner    |productId| cost|
      |"noor"      |"101"   |80.5|

  Scenario Outline: failed to edit product cost
    Given the owner in Product page
    When the owner <owner> enters the option to edit product cost
    And set invalid product Id=<productId> new product cost =<cost>
    Then faild to edit the product cost  And a  message is displayed
    Examples:
      |owner   |productId| cost|
      |"noor"    |"000"   |80.5 |

  Scenario Outline: delete product
    Given the owner in Product page
    When the owner <owner> enters the option to delete product
    And set product Id=<productId>
    Then the product delete successfully a  message is displayed
    Examples:
      |owner    | productId|
      |"noor"      |"101"   |

  Scenario Outline:failed to delete product
    Given the owner in Product page
    When the owner <owner> enters the option to delete product
    And set invalid product Id=<productId>
    Then the product delete failed a  message is displayed
    Examples:
      |owner     | productId|
      |"noor"    |"000"   |

  Scenario Outline: View Discount Product
    Given the owner in Product page
    When the owner <owner> enters the option to View Discount Product
    Then discount product show
    Examples:
      |owner   |
      |"noor" |
  Scenario Outline: Discount rule
    Given the owner in Product page
    When the owner <owner> enters the option to set Discount rule
    And set percentage =<percentage> day before expiration=<day>
    Then the Discount rule Applied successfully and a  message is displayed
    Examples:
      |owner   | percentage| day|
      |"noor"   |10    |8          |




  Scenario: Customer views all available products
    When the customer requests to view all products from shop "sweetee"
    Then the product details are displayed with discounts and feedbacks
