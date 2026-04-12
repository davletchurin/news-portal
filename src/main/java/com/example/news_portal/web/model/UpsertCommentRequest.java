package com.example.news_portal.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpsertCommentRequest {
    @NotBlank(message = "Comment description must be specified!")
    @Size(min = 1, max = 500, message = "Description cannot be less than {min} and greater than {max}!")
    private String description;
    @NotNull(message = "User ID must be specified!")
    @Positive(message = "User ID must be greater than 0!")
    private Long userId;
    @NotNull(message = "News ID must be specified!")
    @Positive(message = "News ID must be greater than 0!")
    private Long newsId;
}
