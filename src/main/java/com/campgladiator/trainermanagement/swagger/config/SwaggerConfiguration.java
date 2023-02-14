package com.campgladiator.trainermanagement.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	@Bean
    public OpenAPI swaggerDocOpenApi() {
        return new OpenAPI()
        				.info(new Info().title("Trainer Management API")
                        .description("Trainer Management is an API to allowed the users to create, list, get and delete objects called Trainer")
                        .version("v0.0.1"));
    }

}
