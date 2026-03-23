package com.example.news_portal.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertNewsCategoryRequest {
    @NotBlank(message = "Category name must be specified!")
    @Size(min = 3, max = 30, message = "Category name cannot be less than {min} and greater than {max}!")
    private String categoryName;
}
