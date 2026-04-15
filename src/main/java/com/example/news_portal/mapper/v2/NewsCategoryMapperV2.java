package com.example.news_portal.mapper.v2;

import com.example.news_portal.entity.NewsCategory;
import com.example.news_portal.web.model.NewsCategoryResponse;
import com.example.news_portal.web.model.UpsertNewsCategoryRequest;
import com.example.news_portal.web.model_v2.list_response.NewsCategoryListShortResponse;
import com.example.news_portal.web.model_v2.response.NewsCategoryShortResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NewsMapperV2.class})
public interface NewsCategoryMapperV2 {
    NewsCategory requestToNewsCategory(UpsertNewsCategoryRequest request);
    @Mapping(source = "categoryId", target = "id")
    NewsCategory requestToNewsCategory(Long categoryId, UpsertNewsCategoryRequest request);

    NewsCategoryResponse newsCategoryToResponse(NewsCategory category);

    NewsCategoryShortResponse newsCategoryToShortResponse(NewsCategory category);

    List<NewsCategoryShortResponse> newsCategoryListToResposeList(List<NewsCategory> categories);
    default NewsCategoryListShortResponse newsCategoryListToNewsCategoryListShortResponse(List<NewsCategory> categories) {
        NewsCategoryListShortResponse response = new NewsCategoryListShortResponse();
        response.setCategories(newsCategoryListToResposeList(categories));
        return response;
    }
}
