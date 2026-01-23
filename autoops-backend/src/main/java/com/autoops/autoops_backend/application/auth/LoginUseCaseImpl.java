package com.autoops.autoops_backend.application.auth;

import com.autoops.autoops_backend.infrastructure.persistence.UserEntity;
import com.autoops.autoops_backend.infrastructure.persistence.UserRepository;
import com.autoops.autoops_backend.infrastructure.security.JwtTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public LoginUseCaseImpl(JwtTokenService jwtTokenService ,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder=  passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public AuthResult login(String email, String password) {

        UserEntity user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Invalid Credentials"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }


        String token = jwtTokenService.generateToken(
                user.getId().toString(),
                user.getRole()
        );

        return new AuthResult(
                user.getId().toString(),
                user.getRole(),
                token
        );
    }
}
