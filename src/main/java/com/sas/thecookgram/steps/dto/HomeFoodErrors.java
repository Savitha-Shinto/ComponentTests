package com.sas.thecookgram.steps.dto;

import org.springframework.http.HttpStatus;

public enum HomeFoodErrors {
    INVALID_USER("Invalid User", 100, HttpStatus.BAD_REQUEST),
    INVALID_RECIPE("Invalid Recipe", 101, HttpStatus.BAD_REQUEST),
    INVALID_GROUP("Invalid Group", 102, HttpStatus.BAD_REQUEST),
    INVALID_GROUPUSER("Invalid Group User Details", 103, HttpStatus.BAD_REQUEST),
    INVALID_BOOKMARK("Invalid Bookmark", 104, HttpStatus.BAD_REQUEST),
    INVALID_SELL("Invalid Sell Details", 105, HttpStatus.BAD_REQUEST),
    RECIPE_TITLE_CANNOT_BE_NULL("Recipe title cannot be null", 106, HttpStatus.BAD_REQUEST),
    RECIPE_DESC_CANNOT_BE_NULL("Recipe description cannot be null", 107, HttpStatus.BAD_REQUEST),
    RECIPE_USER_ID_CANNOT_BE_NULL("Recipe user Id cannot be null", 108, HttpStatus.BAD_REQUEST),
    RECIPE_IMAGE_CANNOT_BE_NULL("Recipe image cannot be null", 109, HttpStatus.BAD_REQUEST),
    USER_ID_CANNOT_BE_NULL("User cannot be null", 110, HttpStatus.BAD_REQUEST),
    FRIEND_ID_CANNOT_BE_NULL("Friend or neighbour  cannot be null", 111, HttpStatus.BAD_REQUEST),
    GROUP_ID_CANNOT_BE_NULL("Group Id cannot be null", 112, HttpStatus.BAD_REQUEST),
    GROUP_NAME_CANNOT_BE_NULL("Group name cannot be null", 113, HttpStatus.BAD_REQUEST),
    NAME_CANNOT_BE_NULL("Name cannot be null", 114, HttpStatus.BAD_REQUEST),
    PASSWORD_CANNOT_BE_NULL("Password cannot be null", 115, HttpStatus.BAD_REQUEST),
    INVALID_FRIEND("Invalid Friend or Neighbour", 116, HttpStatus.BAD_REQUEST),
    JWT_ERROR("JWT Error", 117, HttpStatus.BAD_REQUEST),
    NO_DATA_FOUND("No data found", 118, HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("Invalid TOKEN", 119, HttpStatus.BAD_REQUEST),
    RECIPE_USER_ALREADY_EXISTS("User already exists", 120, HttpStatus.BAD_REQUEST),
    INVALID_LOGIN("Invalid login", 121, HttpStatus.BAD_REQUEST),
    SEND_EMAIL_ERROR("Error in SMPT server", 122, HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS("Data already exists", 123, HttpStatus.BAD_REQUEST),
    IMAGE_PROCESSING_ERROR("Error in image processing", 124, HttpStatus.BAD_REQUEST),
    JWT_TOKEN_ERROR( "Token not found", 125, HttpStatus.BAD_REQUEST),
    CRYPTO_ENCRPTION_ERROR("Encryption failed", 126, HttpStatus.BAD_REQUEST),
    CRYPTO_DECRPTION_ERROR("Decryption failed", 127, HttpStatus.BAD_REQUEST),
    CRYPTO_ALIAS_ERROR("Alias for key not found", 128, HttpStatus.BAD_REQUEST),
    IMAGE_LOCATION_NOT_FOUND("Image location not found", 129, HttpStatus.BAD_REQUEST),
    CONFIRM_PASSWORD_CANNOT_BE_NULL("Confirm password cannot be null", 130, HttpStatus.BAD_REQUEST),
    CONFIRM_PASSWORD_NOT_EQUAL("Password and Confirm password is not matched", 131, HttpStatus.BAD_REQUEST),
    EMAIL_CANNOT_BE_NULL("Email not valid", 132, HttpStatus.BAD_REQUEST),
    POST_TITLE_CANNOT_BE_NULL("Post title cannot be null", 133, HttpStatus.BAD_REQUEST),
    POST_DESC_CANNOT_BE_NULL("Post description cannot be null", 134, HttpStatus.BAD_REQUEST),
    UNKNOWN_ERROR("Unknown Error", 999, HttpStatus.BAD_REQUEST);


    private final String errorMessage;
    private final long errorCode;
    private final HttpStatus httpStatusCode;

    HomeFoodErrors(String errorMessage, long errorCode, HttpStatus httpStatusCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
}
