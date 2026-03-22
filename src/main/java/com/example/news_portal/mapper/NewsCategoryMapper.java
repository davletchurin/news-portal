package com.example.news_portal.mapper;

import com.example.news_portal.entity.NewsCategory;
import com.example.news_portal.web.model.NewsCategoryListResponse;
import com.example.news_portal.web.model.NewsCategoryResponse;
import com.example.news_portal.web.model.UpsertNewsCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NewsMapper.class})
public interface NewsCategoryMapper {
    NewsCategory requestToNewsCategory(UpsertNewsCategoryRequest request);
    @Mapping(source = "categoryId", target = "id")
    NewsCategory requestToNewsCategory(Long categoryId, UpsertNewsCategoryRequest request);
    NewsCategoryResponse newsCategoryToResponse(NewsCategory category);
    List<NewsCategoryResponse> newsCategoryListToResposeList(List<NewsCategory> categories);
    default NewsCategoryListResponse newsCategoryListToNewsCategoryListResponse(List<NewsCategory> categories) {
        NewsCategoryListResponse response = new NewsCategoryListResponse();
        response.setCategories(newsCategoryListToResposeList(categories));
        return response;
    }
}
