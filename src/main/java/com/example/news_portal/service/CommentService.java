package com.example.news_portal.service;

import com.example.news_portal.entity.Comment;
import com.example.news_portal.web.model.CommentFilter;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    List<Comment> findAllNewsId(CommentFilter filter);
    Comment findById(Long id);
    Comment save(Comment comment);
    Comment update(Comment comment);
    void deleteById(Long id);
    long countByNewsId(Long newsId);
}