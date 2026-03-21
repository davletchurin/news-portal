package com.example.news_portal.web.model;

import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String description;
    private Long userId;
    private Long newsId;
}
