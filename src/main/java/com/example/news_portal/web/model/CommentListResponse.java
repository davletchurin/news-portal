package com.example.news_portal.web.model;

import lombok.Data;

import java.util.List;

@Data
public class CommentListResponse {
    private List<CommentResponse> comments;
}
