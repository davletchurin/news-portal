package com.example.news_portal.repository;

import com.example.news_portal.entity.Comment;
import com.example.news_portal.entity.News;
import com.example.news_portal.web.model.CommentFilter;
import org.springframework.data.jpa.domain.Specification;

public interface CommentSpecification {
    static Specification<Comment> withFilter(CommentFilter commentFilter) {
        return Specification.<Comment>unrestricted().and(byNewsId(commentFilter.getNewsId()));
    }

    static Specification<Comment> byNewsId(Long newsId) {
        return (root, query, cb) -> {
            if (newsId == null) return null;
            return cb.equal(root.get(Comment.Fields.news).get(News.Fields.id), newsId);
        };
    }
}
