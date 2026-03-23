package com.example.news_portal.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpsertCommentRequest {
    @NotBlank(message = "Comment description must be specified!")
    private String description;
    @NotNull(message = "User ID must be specified!")
    @Positive(message = "User ID must be greater than 0!")
    private Long userId;
    @NotNull(message = "News ID must be specified!")
    @Positive(message = "News ID must be greater than 0!")
    private Long newsId;
}
