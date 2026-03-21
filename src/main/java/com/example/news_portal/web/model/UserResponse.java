package com.example.news_portal.web.model;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String userName;
    private List<CommentResponse> comments;
    private List<NewsResponse> news;
}
