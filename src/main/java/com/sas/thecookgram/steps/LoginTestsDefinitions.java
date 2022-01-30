package com.sas.thecookgram.steps;

import com.sas.thecookgram.steps.dto.RecipeUserDto;
import com.sas.thecookgram.steps.dto.UserAuth;
import cucumber.api.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class LoginTestsDefinitions extends AbstractSteps {

    @Autowired
    private RestTemplate restTemplate;
    private ResponseEntity responseEntity;
    private RecipeUserDto recipeUserDto;

    @Before
    public void before(Scenario scenario) {

    }

    @When("a user enter {string} and {string}")
    public void aUserEnterAnd(String email, String password) {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);
        try {
            responseEntity = restTemplate.postForEntity(TheCookGramConstants.LOGIN_URL, userAuth, RecipeUserDto.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Then("it should return a response with a {string} response code")
    public void itShouldReturnAResponseWithAResponseCode(String httpStatus) {
        Assertions.assertEquals(httpStatus, String.valueOf(responseEntity.getStatusCode().value()));
        recipeUserDto = (RecipeUserDto) responseEntity.getBody();
        Assertions.assertNotNull(recipeUserDto.getToken());
        Assertions.assertNotNull(recipeUserDto.getId());
    }

    @When("a user enter {string} and {string} then return {string}")
    public void aUserEnterAndThenReturn(String email, String password, String httpStatus) {

        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);
        try{
            responseEntity = restTemplate.postForEntity(TheCookGramConstants.LOGIN_URL, userAuth, RecipeUserDto.class);
        } catch (HttpClientErrorException e){
            Assertions.assertEquals(httpStatus, String.valueOf(e.getStatusCode().value()));
        }

    }

    @Given("I am logged in as a user")
    public void iAmLoggedInAsAUser() {
    }

    @And("user wants to create a recipe with the following attributes")
    public void userWantsToCreateARecipeWithTheFollowingAttributes() {
    }

    @When("user saves the new recipe")
    public void userSavesTheNewRecipe() {
    }

    @Given("I am logged in as a user {string} and {string}")
    public void iAmLoggedInAsAUserAnd(String email, String password) {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.LOGIN_URL, userAuth, RecipeUserDto.class);
        Assertions.assertEquals("200", String.valueOf(responseEntity.getStatusCode().value()));
        recipeUserDto = (RecipeUserDto) responseEntity.getBody();
        Assertions.assertNotNull(recipeUserDto.getToken());
        Assertions.assertNotNull(recipeUserDto.getId());
        payloadContext().setTOKEN(recipeUserDto.getToken());
        payloadContext().setUserId(recipeUserDto.getId());
        payloadContext().setPayload(recipeUserDto);
    }
}
