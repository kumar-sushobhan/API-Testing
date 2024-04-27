package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    static String access_token;
    static Instant expiry_time;

    private static Response renewToken() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("client_id", "0718b2755f82450bbc362018554c7da7");
        formParams.put("client_secret", "5e36c899f78a4777a2803b101684c10f");
        formParams.put("refresh_token", "AQBXbdK_O-qCwQQfo_Zn3CvR7YF5-LqjYrNyJKXuz2wI8_m4suVJG9_kAsXYNo0QicPQpv2O9qXsuehy4ytuAiIGVcp35cPiq80IcbZZR0QqNQWv72I-lBU_IMaqetHDa2w");
        formParams.put("grant_type", "refresh_token");

        Response response = RestResource.postAccount(formParams);
        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT -- RENEW TOKEN FAILED....");
        }
        return response;
    }

    public static String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing token....");
                access_token = renewToken().path("access_token");
                int expiresIn = renewToken().path("expires_in");
                // current time
                expiry_time = Instant.now().plusSeconds(expiresIn - 300); //3600-300
            } else {
                System.out.println("Token is good to use...");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get token....");
        }
        return access_token;
    }
}
