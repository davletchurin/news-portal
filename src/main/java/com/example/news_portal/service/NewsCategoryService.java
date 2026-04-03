package com.example.news_portal.service;

import com.example.news_portal.entity.NewsCategory;
import com.example.news_portal.web.model.NewsCategoryFilter;

import java.util.List;

public interface NewsCategoryService {
    List<NewsCategory> findAll();
    List<NewsCategory> findAll(NewsCategoryFilter filter);
    NewsCategory findById(Long id);
    NewsCategory save(NewsCategory category);
    NewsCategory update(NewsCategory category);
    void deleteById(Long id);
}
