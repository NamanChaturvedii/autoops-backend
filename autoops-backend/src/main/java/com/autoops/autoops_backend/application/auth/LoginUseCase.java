package com.autoops.autoops_backend.application.auth;

public interface LoginUseCase  {

    AuthResult login(String email, String password);

}
