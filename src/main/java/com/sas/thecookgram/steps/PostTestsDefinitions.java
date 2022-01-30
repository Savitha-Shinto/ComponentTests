package com.sas.thecookgram.steps;

import com.sas.thecookgram.steps.dto.PostCommentDto;
import com.sas.thecookgram.steps.dto.PostDto;
import com.sas.thecookgram.steps.dto.PostLikeDto;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class PostTestsDefinitions extends AbstractSteps {
    @Autowired
    private RestTemplate restTemplate;
    private ResponseEntity responseEntity;
    private PostDto addedPost;
    private PostCommentDto commentDto;
    public PostTestsDefinitions() {
        addedPost = new PostDto();
        commentDto = new PostCommentDto();
    }

 
    @Then("I enter all required details for post")
    public void iEnterAllRequiredDetailsForPost(Map map) throws IOException {
        PostDto postDto  = getPostDto(map);

        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/post/createpost", postDto, PostDto.class);
        addedPost = (PostDto) responseEntity.getBody();
        payloadContext().setPayload(responseEntity);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    private PostDto getPostDto(Map map) {
        PostDto postDto = new PostDto();
        postDto.setDesc(map.get("desc").toString());
        postDto.setPostImage(map.get("postImage").toString());
        postDto.setUserId(payloadContext().getUserId());
        return postDto;
    }

    @Then("user saves the new post and it should return a response with a |{int}| response code")
    public void userSavesTheNewPostAndItShouldReturnAResponseWithAResponseCode(int httpStatus) {
        Assertions.assertEquals(httpStatus, responseEntity.getStatusCode().value());
    }


    @Then("I edit my post")
    public void iEditMyPost(Map map) throws IOException {


        PostDto postDto = (PostDto) responseEntity.getBody();
        PostDto Post = getPostDto(map);
        Post.setId(postDto.getId());
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        restTemplate.put(TheCookGramConstants.API_URL + "/post/updatepost", Post);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Then("I delete my post")
    public void iDeleteMyPost() throws IOException {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/post/deletepost", addedPost, Long.class);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }


    @Then("I check all my post")
    public void iCheckAllMyPost() {

        PostDto postDto = (PostDto) responseEntity.getBody();
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/post/getposts", postDto, PostDto[].class);
        boolean isPostinDB = false;
        Object myList = responseEntity.getBody();
        PostDto[] postDtos = (PostDto[]) myList;
        for (int i = 0; i <= postDtos.length - 1; i++) {
            if (postDtos[i].getId() == postDto.getId()) {
                isPostinDB = true;
                break;
            }
        }
        Assertions.assertTrue(isPostinDB);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }


    public void likePost() {
        PostLikeDto like  = new PostLikeDto();
        like.setPostId((int) addedPost.getId());
        like.setUserId((int) addedPost.getUserId());
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/postlike/create", like, PostLikeDto.class);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }


    public void unlikePost() {
        PostLikeDto like  = new PostLikeDto();
        like.setPostId((int) addedPost.getId());
        like.setUserId((int) addedPost.getUserId());
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/postlike/create", like, PostLikeDto.class);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Then("I add a comment to my post")
    public void iAddACommentToMyPost(Map map) {


    }



    @Then("I like my post and the like count should be {int}")
    public void iLikeMyPostAndTheLikeCountShouldBe(int count) {
        likePost();
        checkLikeCount(count);
    }

    public void checkLikeCount(int count) {
        PostDto post = new PostDto();
        post.setId(addedPost.getId());
        post.setUserId(addedPost.getUserId());
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/post/getposts", post, PostDto[].class);
        boolean isPostinDB = false;
        Object posts = responseEntity.getBody();
        PostDto[] postDtos = (PostDto[]) posts;
        PostDto postLikeCount = new PostDto();
        for (int i = 0; i <= postDtos.length - 1; i++) {
            if (postDtos[i].getId() == addedPost.getId()) {
                postLikeCount = postDtos[i];
                isPostinDB = true;
                break;
            }
        }
        Assertions.assertEquals(postLikeCount.getTotallikecountperpost(),count);
        Assertions.assertTrue(isPostinDB);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I unlike my post and the like count should be {int}")
    public void iUnlikeMyPostAndTheLikeCountShouldBe(int count) {
        unlikePost();
        checkLikeCount(count);
    }

    @Then("I add a comment to my post {string}")
    public void addComments(String comment) {
        PostCommentDto commentDto  = new PostCommentDto();
        commentDto.setPostId((int) addedPost.getId());
        commentDto.setUserId((int) addedPost.getUserId());
        commentDto.setComment(comment);
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/postcomment/createcomment", commentDto, PostCommentDto.class);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Then("I update the previous comment to my post {string}")
    public void updateComment(String comment) {
        commentDto  =  (PostCommentDto) responseEntity.getBody();
        commentDto.setComment(comment);
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        restTemplate.put(TheCookGramConstants.API_URL + "/postcomment/updatecomment", commentDto);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Then("I delete my comment")
    public void deleteComments() {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/postcomment/deletecomment", commentDto, Long.class);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Then("I read all comments for this post")
    public void readAllComments() {
        restTemplate.setInterceptors(TheCookGramConstants.getHttpHeaders(payloadContext().getTOKEN()));
        responseEntity = restTemplate.postForEntity(TheCookGramConstants.API_URL + "/post/getposts", commentDto, PostCommentDto[].class);
        boolean isPostinDB = false;
        Object comments = responseEntity.getBody();
        PostCommentDto[] commentDtos = (PostCommentDto[]) comments;
        for (int i = 0; i <= commentDtos.length - 1; i++) {
            if (commentDtos[i].getId() == commentDto.getId()) {
                isPostinDB = true;
            }
        }
        Assertions.assertTrue(isPostinDB);
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
    }
}
