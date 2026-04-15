package com.example.news_portal.mapper.v2;

import com.example.news_portal.entity.Comment;
import com.example.news_portal.service.NewsService;
import com.example.news_portal.service.UserService;
import com.example.news_portal.web.model.CommentResponse;
import com.example.news_portal.web.model.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CommentMapperDelegateV2 implements CommentMapperV2 {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;
    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setDescription(request.getDescription());
        comment.setUser(userService.findById(request.getUserId()));
        comment.setNews(newsService.findById(request.getNewsId()));
        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsertCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);
        return comment;
    }

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setDescription(comment.getDescription());
        response.setNewsId(comment.getNews().getId());
        response.setUserId(comment.getUser().getId());
        return response;
    }

    @Override
    public List<CommentResponse> commentListToResponseList(List<Comment> comments) {
        return comments.stream()
                .map(this::commentToResponse)
                .toList();
    }
}
