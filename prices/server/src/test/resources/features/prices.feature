Feature: Product Price Request

  Scenario Outline: Request for product 35455 and brand 1 at a specific time
    Given the current date is "<datetime>"
    When a user makes a request for product 35455 and brand 1
    Then the system should return the product price information with price value <expectedPrice>

    Examples:
      | datetime            | expectedPrice |
      | 2020-06-14T10:00:00 | 35.50         |
      | 2020-06-14T16:00:00 | 25.45         |
      | 2020-06-14T21:00:00 | 35.50         |
      | 2020-06-15T10:00:00 | 30.50         |
      | 2020-06-16T21:00:00 | 38.95         |
