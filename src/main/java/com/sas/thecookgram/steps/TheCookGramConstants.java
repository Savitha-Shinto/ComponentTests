package com.sas.thecookgram.steps;


import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.ArrayList;
import java.util.List;

public class TheCookGramConstants {
    public static final String API_URL = "http://localhost:9002/api";
    public static final String LOGIN_URL = "http://localhost:9002/auth/login";

    public static List<ClientHttpRequestInterceptor> getHttpHeaders(String token){

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpHeaderInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HttpHeaderInterceptor("ContentType", MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HttpHeaderInterceptor("Authorization", "Bearer "+ token ));
        return interceptors;

    }
}
