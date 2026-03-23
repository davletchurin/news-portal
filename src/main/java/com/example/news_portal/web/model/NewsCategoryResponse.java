package com.example.news_portal.web.model;

import lombok.Data;

import java.util.List;

@Data
public class NewsCategoryResponse {
    private Long id;
    private String categoryName;
    private List<NewsResponse> news;
}
