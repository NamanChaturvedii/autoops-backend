package com.autoops.autoops_backend.domain.user;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class User {
    private String id;
    private String email;
    private String passwordHash;

    private Role role;
    private UserStatus status;

    private Instant createdAt;
    private Instant updatedAt;




}
