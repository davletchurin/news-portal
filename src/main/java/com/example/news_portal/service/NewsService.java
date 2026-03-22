package com.example.news_portal.service;

import com.example.news_portal.entity.News;

import java.util.List;

public interface NewsService {
    List<News> findAll();
    News findById(Long id);
    News save(News news);
    News update(News news);
    void deleteById(Long id);
}
