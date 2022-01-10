package com.jmontanez.challenge.bcp.configuration;

import com.jmontanez.challenge.bcp.configuration.properties.SwaggerProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties propertiesConfig;

    @Bean
    public OpenAPI apiInfo() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(getApiInfo());
    }

    private Contact getSwaggerContact() {
        return new Contact()
                .name(propertiesConfig.getContactName())
                .email(propertiesConfig.getContactEmail())
                .url(propertiesConfig.getContactUrl());
    }
    private Info getApiInfo() {
        return new Info()
                .title(propertiesConfig.getTitle())
                .description(propertiesConfig.getDescription())
                .version(propertiesConfig.getVersion())
                .contact(getSwaggerContact());
    }

}
