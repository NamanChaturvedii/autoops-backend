package com.autoops.autoops_backend;

import com.autoops.autoops_backend.infrastructure.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class AutoopsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoopsBackendApplication.class, args);
	}

}
