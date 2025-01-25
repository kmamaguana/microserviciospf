package com.projectfinal.loginfashionhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    /**
     * Grouping public API endpoints for Swagger UI.
     *
     * @return a GroupedOpenApi instance for public APIs
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")  // Group name
                .pathsToMatch("/**")  // Matches all paths
                .build();
    }

    /**
     * Customizes the OpenAPI documentation metadata for the Fashion Hub API.
     *
     * @return an OpenAPI instance with custom metadata
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fashion Hub API")  // Title of the API
                        .version("1.0")  // API version
                        .description("API for managing user authentication and fashion-related operations.")  // Description of the API
                        .termsOfService("https://fashionhub.com/terms")  // Terms of service URL
                        .contact(new Contact()
                                .name("Support Team")
                                .url("https://fashionhub.com/support")
                                .email("support@fashionhub.com"))  // Contact information
                        .license(new License()
                                .name("Apache 2.0")  // License name
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))  // License URL
                )
                .servers(List.of(
                        new Server().url("http://localhost:3001").description("Local server"),
                        new Server().url("https://api.fashionhub.com").description("Production server")
                ));
    }
}
