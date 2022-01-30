package com.sas.thecookgram.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.thecookgram.steps.dto.BookmarkDto;
import com.sas.thecookgram.steps.dto.RecipeDto;
import com.sas.thecookgram.steps.dto.RecipeUserDto;
import io.cucumber.java.DocStringType;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class RecipeTestsDefinitions extends AbstractSteps {
    @Autowired
    private RestTemplate restTemplate;
    private ResponseEntity responseEntity;
    private RecipeUserDto recipeUserDto;
    private RecipeDto recipeAdded;
    private BookmarkDto bookmarkDto;

    public RecipeTestsDefinitions() {
        recipeAdded = new RecipeDto();
        bookmarkDto = new BookmarkDto();
    }

    @DocStringType
    public Map json(String docString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(docString.toString(), Map.class);
    }

    @Then("I enter all required details for recipe")
    public void iEnterAllRequiredDetailsForRecipe(Map map) throws IOException {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto = getRecipeDto(map);
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/recipe/createrecipe", recipeDto, RecipeDto.class);
        recipeAdded = (RecipeDto) responseEntity.getBody();
        payloadContext().setPayload(responseEntity);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    private RecipeDto getRecipeDto(Map map) {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setAllergens(map.get("allergens").toString());
        recipeDto.setCookingTime(map.get("cookingTime").toString());
        recipeDto.setCuisine(map.get("cuisine").toString());
        recipeDto.setDescription(map.get("description").toString());
        recipeDto.setIngredients(map.get("ingredients").toString());
        recipeDto.setPreparation(map.get("preparation").toString());
        recipeDto.setPreparationTime(map.get("preparationTime").toString());
        recipeDto.setRecipeImage(map.get("recipeImage").toString());
        recipeDto.setServes(map.get("serves").toString());
        recipeDto.setTitle(map.get("title").toString());
        recipeDto.setUserId(payloadContext().getUserId());
        recipeDto.setVideoLink(map.get("videoLink").toString());
        return recipeDto;
    }


    @Then("I get all my recipe")
    public void iGetAllMyRecipe() throws IOException {

        RecipeDto recipeDto = (RecipeDto) responseEntity.getBody();
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/recipe/getrecipes", recipeDto, RecipeDto[].class);
        boolean isRecipeinDB = false;
        Object myList = responseEntity.getBody();
        RecipeDto[] recipeDtos = (RecipeDto[]) myList;
        for (int i = 0; i <= recipeDtos.length - 1; i++) {
            if (recipeDtos[i].getId() == recipeDto.getId()) {
                isRecipeinDB = true;
                break;
            }
        }
        Assertions.assertTrue(isRecipeinDB);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I edit my recipe")
    public void iEditMyRecipe(Map map) throws IOException {
        RecipeDto recipeDto = (RecipeDto) responseEntity.getBody();
        RecipeDto recipe = getRecipeDto(map);
        recipe.setId(recipeDto.getId());
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        restTemplate.put(TheCookGramConstants.API_URL + "/recipe/updaterecipe", recipe);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Then("I delete my recipe")
    public void iDeleteMyRecipe() throws IOException {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/recipe/deleterecipe", recipeAdded, Long.class);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I save newly created post")
    public void iSaveNewlyCreatedPost(int httpStatus) {
        Assertions.assertEquals(httpStatus, responseEntity.getStatusCode().value());
    }

    @Then("I search for recipe {string}")
    public void iSearchForRecipe(String recipe) {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.getForEntity(TheCookGramConstants.API_URL + "/recipe/getrecipesbytitle/"+recipe,  RecipeDto[].class);
        Object myRecipeList = responseEntity.getBody();
        boolean isRecipeinDB = false;
        RecipeDto[] recipeList = (RecipeDto[]) myRecipeList;
        for (int i = 0; i <= recipeList.length - 1; i++) {
            if (recipeList[i].getTitle().equals(recipe)) {
                isRecipeinDB = true;
                break;
            }
        }
        Assertions.assertTrue(isRecipeinDB);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

}
