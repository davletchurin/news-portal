package com.example.news_portal.mapper;

import com.example.news_portal.model.News;
import com.example.news_portal.web.model.NewsListResponse;
import com.example.news_portal.web.model.NewsResponse;
import com.example.news_portal.web.model.UpsertNewsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    News requestToNews(UpsertNewsRequest request);
    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);
    List<NewsResponse> newsListToResponseList(List<News> news);
    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        NewsListResponse response = new NewsListResponse();
        response.setNews(newsListToResponseList(news));
        return response;
    }
}
