package com.autoops.autoops_backend.application.auth;

public class AuthResult {

    private final String userId;
    private final String role;
    private final String token;

    public AuthResult(String userId,String role , String token){
        this.userId = userId;
        this.role = role;
        this.token = token;
    }

    public String getUserId(){
        return userId;
    }

    public String getRole(){
        return role;
    }

    public String getToken(){
        return token;
    }

}
