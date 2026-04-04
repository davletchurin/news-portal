package com.example.news_portal.repository;

import com.example.news_portal.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    long countByNewsId(Long newsId);
}
