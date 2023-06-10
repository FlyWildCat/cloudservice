package ru.pda.cloudservice.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthToken {
    @JsonProperty("auth-token")
    private String token;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
