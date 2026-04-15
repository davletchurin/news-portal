package com.example.news_portal.web.model;

import com.example.news_portal.web.model_v2.response.NewsShortResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsCategoryResponse {
    private Long id;
    private String categoryName;
    private List<NewsShortResponse> news = new ArrayList<>();
}
