package com.example.news_portal.web.model_v2.response;

import lombok.Data;

@Data
public class NewsShortResponse {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private Long userId;
}
