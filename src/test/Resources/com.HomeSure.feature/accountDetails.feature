Feature: updating customer account details

  Scenario: admin should be able to check the account details of a particular customer using a valid token and account id
    Given I perform a GET request for the account details with valid credentials
    Then  I should be able to receive that particular customer details
    And I should be able to assert response code 200

  Scenario: admin should not be able to check the customer account details without valid token and account id
    Given I perform a GET request for the account details with invalid token
    Then I should get an error message with description station that the token is invalid

  Scenario: admin should be able to update the customer account details by providing valid values for mandatory keys
    Given I perform a PATCH request on particular account details by providing valid token and account_id
    Then I should be able to update the details
    And I should be able to assert the updating by a GET request

  Scenario: admin should not be able to update the customer account details by providing invalid values for mandatory keys
    Given I perform a PATCH request on particular account details by providing invalid token
    Then I should receive an error description on the invalidity of the token provided
    And  i should be able to verify that none of the details are updated