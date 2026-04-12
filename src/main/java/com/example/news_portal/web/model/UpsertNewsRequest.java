package com.example.news_portal.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertNewsRequest {
    @NotBlank(message = "News title must be specified!")
    @Size(min = 1, max = 100, message = "Title cannot be less than {min} and greater than {max}!")
    private String title;
    @NotBlank(message = "News description must be specified!")
    @Size(min = 1, max = 500, message = "Description cannot be less than {min} and greater than {max}!")
    private String description;
    @NotNull(message = "Category ID must be specified!")
    @Positive(message = "Category ID must be greater than 0!")
    private Long categoryId;
    @NotNull(message = "User ID must be specified!")
    @Positive(message = "User ID must be greater than 0!")
    private Long userId;
}
