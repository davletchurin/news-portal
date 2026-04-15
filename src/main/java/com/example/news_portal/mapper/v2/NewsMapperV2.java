package com.example.news_portal.mapper.v2;

import com.example.news_portal.entity.News;
import com.example.news_portal.web.model.NewsListResponse;
import com.example.news_portal.web.model.NewsResponse;
import com.example.news_portal.web.model.NewsResponseWithCommentsCount;
import com.example.news_portal.web.model.UpsertNewsRequest;
import com.example.news_portal.web.model_v2.response.NewsShortResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(NewsMapperDelegateV2.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CommentMapperV2.class})
public interface NewsMapperV2 {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    NewsResponseWithCommentsCount newsToResponseWithCommentsCount(News news);
    List<NewsResponseWithCommentsCount> newsListToResponseWithCommentsCountList(List<News> news);
    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        NewsListResponse response = new NewsListResponse();
        response.setNews(newsListToResponseWithCommentsCountList(news));
        return response;
    }
    NewsShortResponse newsToShortResponse(News news);
}
