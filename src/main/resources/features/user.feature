Feature: Post

  Scenario: Successful User Create, Edit, Get and Delete

    Given I am logged in as a user "savi@test.com" and "password"
    Then I enter all required details for user
      """json
          {
            "emailId": "support@tcg.com",
            "name": "support user",
            "password": "password",
            "confirmpassword": "password",
            "country": "UK",
            "county": "hampshire",
            "town": "fleet",
            "userImage": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD"
          }
      """
     Then I edit my user
      """json
          {
            "emailId": "support@tcg.com",
            "name": "support user",
            "password": "password1",
            "confirmpassword": "password1",
            "country": "UK",
            "county": "hampshire",
            "town": "fleet",
            "userImage": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD"
          }
      """
     Then I check created user
     Then I add user two
        """json
            {
              "emailId": "support1@tcg.com",
              "name": "support1 user",
              "password": "password",
              "confirmpassword": "password",
              "country": "UK",
              "county": "hampshire",
              "town": "fleet",
              "userImage": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD"
            }
        """
    Then I add user three
        """json
            {
              "emailId": "support2@tcg.com",
              "name": "support2 user",
              "password": "password",
              "confirmpassword": "password",
              "country": "UK",
              "county": "hampshire",
              "town": "fleet",
              "userImage": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD"
            }
        """
     Then I add user one as a fan for user two
     Then I read the added fan
     Then I search for fans
     Then I delete fan following
     Then I delete all users





