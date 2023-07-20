Feature: New user

  Scenario Outline: Create a new user
    Given I have email "<email>"
    Given I have random username <username>
    Given I have password "<password>"
    When I click on sign up
    Then I get a new user "<user>"

    Examples:
      | email             | username | password     | user |
      | slGGuu@Ll.com     | 5        | Hejsansa123! | yes  |
      | dajiSAuia@sad.se  | 101      | Hejsan123!   | no   |
      | iwoASalskdj@oo.se | 3        | Hejsan123!   | no   |
      |                   | 7        | Hejsan123!!  | no   |




