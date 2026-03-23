package com.example.news_portal.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertUserRequest {
    @NotBlank(message = "User name must be specified!")
    @Size(min = 3, max = 30, message = "User name cannot be less than {min} and greater than {max}!")
    private String userName;
}
