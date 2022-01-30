Feature: Login

  Scenario Outline: login to thecookgram returns a valid token
    When a user enter "<email>" and "<password>"
    Then it should return a response with a "<httpStatus>" response code
    Examples:
    |email         |password|httpStatus|
    |savi@test.com |password|200       |

  Scenario Outline: login to thecookgram  with  invalid password returns a bad request
    When a user enter "<email>" and "<password>" then return "<httpStatus>"
    Examples:
      |email         |password|httpStatus|
      |savi@test.com |password1|400       |

  Scenario Outline: login to thecookgram  with no email id details returns a bad request
    When a user enter "<email>" and "<password>" then return "<httpStatus>"
    Examples:
      |email         |password|httpStatus|
      | |password1|400       |

  Scenario Outline: login to thecookgram  with no password details returns a bad request
    When a user enter "<email>" and "<password>" then return "<httpStatus>"
    Examples:
      |email         |password|httpStatus|
      | | |400       |