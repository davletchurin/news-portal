package com.example.news_portal.repository;

import com.example.news_portal.entity.News;
import com.example.news_portal.entity.NewsCategory;
import com.example.news_portal.entity.User;
import com.example.news_portal.web.model.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {
    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.<News>unrestricted()
                .and(byUserId(newsFilter.getUserId()))
                .and(byCategoryId(newsFilter.getCategoryId()));
    }

    static Specification<News> byUserId(Long userId) {
        return (root, query, cb) -> {
            if (userId == null) return null;
            return cb.equal(root.get(News.Fields.user).get(User.Fields.id), userId);
        };
    }

    static Specification<News> byCategoryId(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return null;
            return cb.equal(root.get(News.Fields.category).get(NewsCategory.Fields.id), categoryId);
        };
    }
}
