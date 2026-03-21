package com.example.news_portal.web.model;

import lombok.Data;

@Data
public class UpsertCommentRequest {
    private String description;
    private Long userId;
    private Long newsId;
}
