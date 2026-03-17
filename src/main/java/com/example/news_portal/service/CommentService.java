package com.example.news_portal.service;

import com.example.news_portal.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(Long id);
    Comment save(Comment news);
    Comment update(Comment news);
    void deleteById(Long id);
}