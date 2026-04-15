package com.example.news_portal.web.model;

import com.example.news_portal.web.model_v2.response.NewsShortResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String userName;
    private List<CommentResponse> comments = new ArrayList<>();
    private List<NewsShortResponse> news = new ArrayList<>();
}
