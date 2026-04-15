package com.example.news_portal.web.model_v2.list_response;

import com.example.news_portal.web.model_v2.response.NewsShortResponse;
import lombok.Data;

import java.util.List;

@Data
public class NewsListShortResponse {
    List<NewsShortResponse> news;
}
