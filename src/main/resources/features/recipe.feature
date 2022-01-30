Feature: Recipe

  Scenario: Successful Recipe Create, Edit, Get and Delete

    Given I am logged in as a user "savi@test.com" and "password"
    Then I enter all required details for recipe
      """json
          {
        "allergens": "cake",
        "cookingTime": "40",
        "cuisine": "English",
        "description": "This cake was sent home from our children's school. It is the simplest, great tasting cake I've ever made. Great to make with the kids, especially for cupcakes.",
        "ingredients": "Ingredients↵1/2 cup (1 stick) unsalted butter (softened and divided into 2-tablespoon pieces; plus more for coating pans)↵2 1/4 cups all-purpose flour (plus more for coating pans)↵1 1/3 cups sugar (white granulated)↵1/2 teaspoon salt.↵1 tablespoon baking powder.↵1 teaspoon vanilla extract.↵1 cup 2 percent milk.↵2 large eggs.↵",
        "preparation": "STEP 1 Heat the oven to 180C/160C fan/gas 4. Melt 1 tbsp butter in a small pan, then stir in ½ tbsp flour to create a wet paste. Brush it over the inside of a 9-inch bundt tin. Put the remaining butter, sugar, treacle and golden syrup in a pan set over a medium heat and stir until everything has melted together. Leave to cool a little.  STEP 2 Pour the mixture into a large bowl and whisk in the eggs and milk. Fold in the stem ginger, remaining flour, salt, ground ginger, cinnamon and bicarb. Tip into the prepared tin and bake for 40-45 mins, or until firm to the touch. Leave to cool for 10 mins in the tin, then turn out onto a wire rack to cool completely.    STEP 3 To make the icing, whisk the milk, ginger syrup and icing sugar together. Melt the chocolate in a heatproof bowl in the microwave in 1-min bursts. Leave to cool a little, then whisk into the milk mixture. Spoon the icing over the cake, then decorate with the crystallised ginger pieces.",
        "preparationTime": "20",
        "recipeImage": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD",
        "serves": "30",
        "title": "Lemon drizil cake",
        "videoLink": "test"
          }
      """

    Then I edit my recipe
      """json
          {
        "allergens": "curry",
        "cookingTime": "40",
        "cuisine": "Indian",
        "description": "curry.",
        "ingredients": "Curry",
        "preparation":"Curry",
        "preparationTime": "20",
        "recipeImage": "",
        "serves": "30",
        "title": "Curry",
        "videoLink": "test"
          }
      """
    Then I get all my recipe
    Then I search for recipe "Curry"
    Then I delete my recipe






