package com.example.news_portal.web.model;

import lombok.Data;

@Data
public class NewsResponseWithCommentsCount {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private Long userId;
    private Long commentsCount;
}
