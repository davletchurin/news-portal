package com.example.news_portal.service.impl;

import com.example.news_portal.entity.News;
import com.example.news_portal.entity.User;
import com.example.news_portal.exception.EntityNotFoundException;
import com.example.news_portal.entity.Comment;
import com.example.news_portal.repository.CommentRepository;
import com.example.news_portal.repository.CommentSpecification;
import com.example.news_portal.service.CommentService;
import com.example.news_portal.service.NewsService;
import com.example.news_portal.service.UserService;
import com.example.news_portal.utils.BeanUtils;
import com.example.news_portal.web.model.CommentFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final NewsService newsService;
    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findAll(CommentFilter filter) {
        return commentRepository.findAll(
                CommentSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("Comment with ID {0} not found!", id))
        );
    }

    @Override
    public Comment save(Comment comment) {
        User user = userService.findById(comment.getUser().getId());
        News news = newsService.findById(comment.getNews().getId());
        comment.setUser(user);
        comment.setNews(news);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        User user = userService.findById(comment.getUser().getId());
        News news = newsService.findById(comment.getNews().getId());
        Comment existedComment = findById(comment.getId());
        BeanUtils.copyNotNullProperties(comment, existedComment);
        existedComment.setUser(user);
        existedComment.setNews(news);
        return commentRepository.save(existedComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public long countByNewsId(Long newsId) {
        return commentRepository.countByNewsId(newsId);
    }
}
