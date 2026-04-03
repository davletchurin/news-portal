package com.example.news_portal.service;

import com.example.news_portal.entity.News;
import com.example.news_portal.web.model.NewsFilter;

import java.util.List;

public interface NewsService {
    List<News> findAll();
    List<News> findAll(NewsFilter filter);
    News findById(Long id);
    News save(News news);
    News update(News news);
    void deleteById(Long id);
}
