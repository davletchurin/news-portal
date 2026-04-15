package com.example.news_portal.mapper.v2;

import com.example.news_portal.entity.News;
import com.example.news_portal.service.CommentService;
import com.example.news_portal.service.NewsCategoryService;
import com.example.news_portal.service.UserService;
import com.example.news_portal.web.model.NewsResponse;
import com.example.news_portal.web.model.NewsResponseWithCommentsCount;
import com.example.news_portal.web.model.UpsertNewsRequest;
import com.example.news_portal.web.model_v2.response.NewsShortResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class NewsMapperDelegateV2 implements NewsMapperV2 {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsCategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapperV2 commentMapper;

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
        news.setId(newsId);
        return news;
    }

    @Override
    public List<NewsResponseWithCommentsCount> newsListToResponseWithCommentsCountList(List<News> news) {
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

    @Override
    public NewsShortResponse newsToShortResponse(News news) {
        NewsShortResponse response = new NewsShortResponse();
        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setDescription(news.getDescription());
        response.setUserId(news.getUser().getId());
        response.setCategoryId(news.getCategory().getId());
        return response;
    }
}
