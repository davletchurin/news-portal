package com.example.news_portal.mapper;

import com.example.news_portal.entity.News;
import com.example.news_portal.service.CommentService;
import com.example.news_portal.service.NewsCategoryService;
import com.example.news_portal.service.UserService;
import com.example.news_portal.web.model.NewsResponse;
import com.example.news_portal.web.model.NewsResponseWithCommentsCount;
import com.example.news_portal.web.model.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class NewsMapperDelegate implements NewsMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsCategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setTitle(request.getTitle());
        news.setDescription(request.getDescription());
        news.setUser(userService.findById(request.getUserId()));
        news.setCategory(categoryService.findById(request.getCategoryId()));
        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(request.getUserId());
        return news;
    }

    @Override
    public List<NewsResponseWithCommentsCount> newsListToResponseList(List<News> news) {
        return news.stream().map(this::newsToResponseWithCommentsCount).toList();
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse response = new NewsResponse();
        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setDescription(news.getDescription());
        response.setUserId(news.getUser().getId());
        response.setCategoryId(news.getCategory().getId());
        response.setComments(commentMapper.commentListToResponseList(news.getComments()));
        return response;
    }

    @Override
    public NewsResponseWithCommentsCount newsToResponseWithCommentsCount(News news) {
        NewsResponseWithCommentsCount response = new NewsResponseWithCommentsCount();
        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setDescription(news.getDescription());
        response.setUserId(news.getUser().getId());
        response.setCategoryId(news.getCategory().getId());
        response.setCommentsCount(commentService.countByNewsId(news.getId()));
        return response;
    }
}
