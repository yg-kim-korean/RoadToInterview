package com.server.RoadToInerview.JWTTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


public class JWTRequestTest extends WebIntegrationTest {

    @DisplayName("1. hello 메세지 받아오기")
    @Test
    void test_1(){
        RestTemplate client = new RestTemplate();
//        client.exchange(uri("/login"), HttpMethod.POST,);
    }
}
