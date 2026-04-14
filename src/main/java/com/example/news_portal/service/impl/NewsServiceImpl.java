package com.example.news_portal.service.impl;

import com.example.news_portal.entity.NewsCategory;
import com.example.news_portal.entity.User;
import com.example.news_portal.exception.EntityNotFoundException;
import com.example.news_portal.entity.News;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.NewsSpecification;
import com.example.news_portal.service.NewsCategoryService;
import com.example.news_portal.service.NewsService;
import com.example.news_portal.service.UserService;
import com.example.news_portal.utils.BeanUtils;
import com.example.news_portal.web.model.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsCategoryService categoryService;
    private final UserService userService;
    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> findAll(NewsFilter filter) {
        return newsRepository.findAll(
                NewsSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("News with ID {0} not found!", id))
        );
    }

    @Override
    public News save(News news) {
        NewsCategory category = categoryService.findById(news.getCategory().getId());
        User user = userService.findById(news.getUser().getId());
        news.setCategory(category);
        news.setUser(user);
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        NewsCategory category = categoryService.findById(news.getCategory().getId());
        User user = userService.findById(news.getUser().getId());
        News existedNews = findById(news.getId());
        BeanUtils.copyNotNullProperties(news, existedNews);
        existedNews.setCategory(category);
        existedNews.setUser(user);
        return newsRepository.save(existedNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
