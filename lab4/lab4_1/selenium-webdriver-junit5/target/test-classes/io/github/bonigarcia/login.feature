Feature: Login in practice site

  Scenario: Successful login
    Given I use "Chrome"
    When I navigate to "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
    And I log in with the username "user" and password "user"
    And I click Submit
    Then I should see the message "Login successful"

  Scenario: Failure login
    Given I use "Chrome"
    When I navigate to "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
    And I log in with the username "bad-user" and password "bad-password"
    And I click Submit
    Then I should see the message "Invalid credentials"