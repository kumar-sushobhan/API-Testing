package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
    @JsonProperty("error")
    private InnerError innerError;

    @JsonProperty("error")
    public InnerError getError() {
        return innerError;
    }

    @JsonProperty("error")
    public void setError(InnerError innerError) {
        this.innerError = innerError;
    }
}
