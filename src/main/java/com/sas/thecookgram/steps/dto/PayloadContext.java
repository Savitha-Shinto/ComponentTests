package com.sas.thecookgram.steps.dto;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.ThreadLocal.withInitial;

public enum PayloadContext {

    CONTEXT;


    public static long getUserId() {
        return userId;
    }

    public static void setUserId(long userId) {
        PayloadContext.userId = userId;
    }

    private static  long userId;
    private static  String TOKEN;
    private static final String PAYLOAD = "PAYLOAD";
    private static final String REQUEST = "REQUEST";
    private static final String RESPONSE = "RESPONSE";
    private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

    public <T> T get(String name) {
        return (T) testContexts.get()
                .get(name);
    }

    public <T> T set(String name, T object) {
        testContexts.get()
                .put(name, object);
        return object;
    }

    public RequestSpecification getRequest() {
        if (null == get(REQUEST)) {
            set(REQUEST, given().log()
                    .all());
        }

        return get(REQUEST);
    }

    public Response getResponse() {
        return get(RESPONSE);
    }

    public Response setResponse(Response response) {
        return set(RESPONSE, response);
    }

    public Object getPayload() {
        return get(PAYLOAD);
    }

    public <T> T getPayload(Class<T> clazz) {
        return clazz.cast(get(PAYLOAD));
    }

    public <T> void setPayload(T object) {
        set(PAYLOAD, object);
    }

    public void reset() {
        testContexts.get()
                .clear();
    }

    public  String getTOKEN() {
        return TOKEN;
    }

    public  void setTOKEN(String TOKEN) {
        PayloadContext.TOKEN = TOKEN;
    }

}
