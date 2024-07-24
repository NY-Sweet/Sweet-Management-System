Feature: Notifications

  Scenario: User gets email when order is placed
    Given I'm the user
    When the user "noor" places an order
    Then send an email to "s12112422@stu.najah.edu" with the message "Thank you for your order, it will be delivered soon."

  Scenario: User gets email when order reject
    Given I'm the user
    When the user's order status changes to delivered
    Then send an email to "s12112422@stu.najah.edu" with the message "Your order has been rejected"

  Scenario: User gets email when order shipped
    Given I'm the user
    When the user's order status changes to delivered
    Then send an email to "s12112422@stu.najah.edu" with the message "Your order has been shipped"

  Scenario: User gets email when order is delivered
    Given I'm the user
    When the user's order status changes to delivered
    Then send an email to "s12112422@stu.najah.edu" with the message "Your order has been delivered."
