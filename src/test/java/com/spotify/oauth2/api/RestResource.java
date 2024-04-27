package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String token, Object requestPlaylist) {
        return given(SpecBuilder.getRequestSpecification())
                .header("Authorization", "Bearer " + token)
                .body(requestPlaylist)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecification())
                .extract()
                .response();
    }

    public static Response postAccount(Map<String, String> formParams) {
        return given(SpecBuilder.getAccountRequestSpecification())
                .formParams(formParams)
                .when()
                .post("/api/token")
                .then()
                .spec(SpecBuilder.getResponseSpecification())
                .extract()
                .response();
    }

    public static Response get(String token, String path) {
        return given(SpecBuilder.getRequestSpecification())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecification())
                .extract()
                .response();
    }

    public static Response put(String token, String path, Object requestPlaylist) {
        return given(SpecBuilder.getRequestSpecification())
                .header("Authorization", "Bearer " + token)
                .body(requestPlaylist)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecification())
                .extract()
                .response();
    }

}
