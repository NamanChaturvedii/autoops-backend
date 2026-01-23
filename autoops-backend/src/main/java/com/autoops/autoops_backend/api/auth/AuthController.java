package com.autoops.autoops_backend.api.auth;

import com.autoops.autoops_backend.application.auth.AuthResult;
import com.autoops.autoops_backend.application.auth.LoginUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private  final LoginUseCase loginUseCase;


    public AuthController( LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;

    }

    @PostMapping("/login")
    public AuthResult login(@RequestBody LoginRequest request){
        return loginUseCase.login(request.getEmail(), request.getPassword());
    }



}
