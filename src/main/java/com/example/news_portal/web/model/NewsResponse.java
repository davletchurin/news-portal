package com.example.news_portal.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsResponse {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private Long userId;
    private List<CommentResponse> comments = new ArrayList<>();
}
