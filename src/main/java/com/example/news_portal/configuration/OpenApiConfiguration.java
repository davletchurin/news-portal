package com.example.news_portal.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApiDescription() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Development server");

        Contact contact = new Contact();
        contact.email("davletchurin@gmail.com");
        contact.name("Ainur Davletchurin");
        contact.url("https://t.me/lugekate");

        ApiResponse badRequest = new ApiResponse()
                .description("Invalid request (validation error)")
                .content(new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().schema(
                                new Schema<>().$ref("#/components/schemas/ErrorResponse")
                        )));

        Info info = new Info();
        info.title("REST API for news portal");
        info.version("1.0.0");
        info.contact(contact);
        info.description("This API provides features for managing a news portal.");
        info.license(new License().name("Apache 2.0").url("http://springdoc.org"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer))
                .components(new Components()
                        .addResponses("BadRequest", badRequest)
                );
    }
}
