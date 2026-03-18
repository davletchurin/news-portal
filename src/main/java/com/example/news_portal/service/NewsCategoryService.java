package com.example.news_portal.service;

import com.example.news_portal.model.NewsCategory;

import java.util.List;

public interface NewsCategoryService {
    List<NewsCategory> findAll();
    NewsCategory findById(Long id);
    NewsCategory save(NewsCategory category);
    NewsCategory update(NewsCategory category);
    void deleteById(Long id);
}
