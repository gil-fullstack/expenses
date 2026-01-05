package com.hightech.expenses.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Expenses API",
                version = "v1",
                description = "API for managing users, accounts, categories and transactions.",
                contact = @Contact(name = "HighTech", email = "support@example.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
)
public class OpenApiConfig {
    // No explicit beans required for basic configuration when using springdoc-openapi-starter-webmvc-ui
}
