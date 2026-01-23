package com.autoops.autoops_backend.api.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminTestController {

        @GetMapping("/admin/test")
        @PreAuthorize("hasRole('ADMIN')")
        public String adminOnly(){
            return "Admin access granted";
        }

}
