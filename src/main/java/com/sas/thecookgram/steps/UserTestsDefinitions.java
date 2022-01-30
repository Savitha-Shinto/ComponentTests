package com.sas.thecookgram.steps;

import com.sas.thecookgram.steps.dto.FanFollowerDto;
import com.sas.thecookgram.steps.dto.RecipeUserDto;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class UserTestsDefinitions extends AbstractSteps {
    @Autowired
    private RestTemplate restTemplate;
    private ResponseEntity responseEntity;

    private RecipeUserDto userOneAdded;
    private RecipeUserDto userTwoAdded;
    private RecipeUserDto userThreeAdded;
    private FanFollowerDto fanAdded;

    public UserTestsDefinitions() {
        userOneAdded = new RecipeUserDto();
        userTwoAdded =  new RecipeUserDto();
        userThreeAdded =  new RecipeUserDto();
        fanAdded =  new FanFollowerDto();
    }

    @Then("I enter all required details for user")
    public void iEnterAllRequiredDetailsForRecipe(Map map) throws IOException {
        addUser(map);
        userOneAdded = (RecipeUserDto) responseEntity.getBody();
    }

    private void addUser(Map map) throws IOException {
        RecipeUserDto recipeUserDto = new RecipeUserDto();
        recipeUserDto = getRecipeUserDto(map);
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        //check user Id exist then delete it and create it
        deleteUserIfExists(recipeUserDto);

        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/recipeuser/createrecipeuser", recipeUserDto, RecipeUserDto.class);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    private void deleteUserIfExists(RecipeUserDto recipeUserDto) throws IOException {
        long id = getAllUsers(recipeUserDto.getEmailId());
        if(id > 0) {
            userOneAdded.setId(id);
            deleteUser(userOneAdded);
        }
    }

    private RecipeUserDto getRecipeUserDto(Map map) {
        RecipeUserDto recipeUserDto = new RecipeUserDto();
        recipeUserDto.setName(map.get("name").toString());
        recipeUserDto.setEmailId(map.get("emailId").toString());
        recipeUserDto.setPassword(map.get("password").toString());
        recipeUserDto.setConfirmpassword(map.get("confirmpassword").toString());
        recipeUserDto.setCountry(map.get("country").toString());
        recipeUserDto.setCounty(map.get("county").toString());

        recipeUserDto.setTown(map.get("town").toString());
        recipeUserDto.setUserImage(map.get("userImage").toString());

        return recipeUserDto;
    }


    @Then("I edit my user")
    public void editUser(Map map) throws IOException {
        RecipeUserDto recipeDto = (RecipeUserDto) responseEntity.getBody();
        RecipeUserDto recipeUserDto = getRecipeUserDto(map);
        recipeUserDto.setId(recipeDto.getId());
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        restTemplate.put(TheCookGramConstants.API_URL + "/recipeuser/updaterecipeuser", recipeUserDto);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }


    public void deleteUser(RecipeUserDto user) throws IOException {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/recipeuser/deleteuser", user, Long.class);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I save newly created user")
    public void iSaveNewlyCreatedPost(int httpStatus) {
        Assertions.assertEquals(httpStatus, responseEntity.getStatusCode().value());
    }


    @Then("I check created user")
    public void iCheckCreatedUser() {
        RecipeUserDto recipeUserDto = (RecipeUserDto) responseEntity.getBody();
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/recipeuser/getrecipeuser", recipeUserDto, RecipeUserDto.class);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }


    public long getAllUsers(String email) throws IOException {

        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.getForEntity(TheCookGramConstants.API_URL + "/recipeuser/allrecipeusers", RecipeUserDto[].class);
        boolean isRecipeUseriInDB = false;
        Object userList = responseEntity.getBody();
        RecipeUserDto[] recipeUserDtos = (RecipeUserDto[]) userList;
        for (int i = 0; i <= recipeUserDtos.length - 1; i++) {
            if (recipeUserDtos[i].getEmailId().equals(email)) {
                return recipeUserDtos[i].getId();
            }
        }
        return 0;
    }

    @Then("I add user two")
    public void iAddAnotherUser(Map map) throws IOException {
        addUser(map);
        userTwoAdded = (RecipeUserDto) responseEntity.getBody();
    }

    @Then("I add user one as a fan for user two")
    public void iAddUserOneAsAFanForUserTwo() {
        FanFollowerDto fanFollowerDto = new FanFollowerDto();
        fanFollowerDto.setFriendId(userTwoAdded.getId());
        fanFollowerDto.setUserId(userThreeAdded.getId());
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/fan/createfanfollowing", fanFollowerDto, FanFollowerDto.class);
        fanAdded = (FanFollowerDto) responseEntity.getBody();
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Then("I delete fan following")
    public void iDeleteFanFollwoing() {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/fan/deletefanfollowing", fanAdded, Long.class);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I read the added fan")
    public void iReadTheAddedFanFollowing() {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/fan/getfanfollowingbyuserid", fanAdded, FanFollowerDto[].class);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I add user three")
    public void iAddUserThree(Map map) throws IOException {
        addUser(map);
        userThreeAdded = (RecipeUserDto) responseEntity.getBody();
    }

    @Then("I search for fans")
    public void iSearchForFans() {
        fanAdded.setSearch("Support");
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/fan/getallchefsnotinmyfollowinglist", fanAdded, FanFollowerDto[].class);
        boolean isFanNotMatchedInDb = false;
        Object fanlist = responseEntity.getBody();
        FanFollowerDto[] fans = (FanFollowerDto[]) fanlist;
        isFanNotMatchedInDb = (fans.length > 0);
        Assertions.assertTrue(isFanNotMatchedInDb);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I delete all users")
    public void iDeleteAllUsers() throws IOException {
        deleteUser(userOneAdded);
        deleteUser(userTwoAdded);
        deleteUser(userThreeAdded);
    }
}
