Feature: automating api test

  @api-get
  Scenario: requesting for GET call
    Given I have base url "https://www.googleapis.com/books/v1/volumes"
    And I set headers "test" to "applicaiton/json"
    And I set params 'q' to 'isbn:9781451648546'
    When I make a GET call ''
    Then verify status code '200'

  @api-users
  Scenario: requesting for GET call
    Given I have base url "https://reqres.in/"
    And I set params 'page' to '2'
    When I make a GET call 'api/users'
    Then verify status code '200'
    And I extract response to json
    Then I extract json path value 'data[0].email'
    Then I get value as 'michael.lawson@reqres.in'
    Then I extract json path value 'data[0].id'
    Then I get value as '7'


  Scenario: requesting for post call
    Given I have base url "https://demoqa.com/BookStore/v1/Books"
    And I set headers "Content-Type" to "application/json"
    And I set params "userId" to "TQ123"
    And I set params 'isbn' to '9781449325862'
    When I make a POST call '/BookStoreV1BooksPost'
    Then verify status code '210'

  @api-post
  Scenario: requesting for post call
    Given I have base url "https://gorest.co.in/public-api/users"
    And I set headers "Content-Type" to "application/json"
    And I set headers "authorization" to "Bearer 0431655cfe7ba40a791e0ce32d83ad33363348919c11627f409a3228f205e15f"
    And I prepare body
      | name   | Naresh                    |
      | gender | Male                      |
      | email  | accolitetestuser@mail.com |
      | status | Active                    |
    When I make a POST call '/User'
    Then verify status code '210'
    And I extract response to json
    Then I extract json path value 'name'
    And I get value as 'Naresh'
    And I extract json path value 'email'
    And I get value as 'accolitetestuser@mail.com'