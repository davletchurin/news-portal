package com.example.news_portal.web.controller;

import com.example.news_portal.aop.CommentAccessVerifiable;
import com.example.news_portal.entity.Comment;
import com.example.news_portal.mapper.CommentMapper;
import com.example.news_portal.service.CommentService;
import com.example.news_portal.web.model.CommentListResponse;
import com.example.news_portal.web.model.CommentResponse;
import com.example.news_portal.web.model.UpsertCommentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<CommentListResponse> findAll() {
        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(commentService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(commentService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request) {
        Comment createdComment = commentService.save(commentMapper.requestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                commentMapper.commentToResponse(createdComment)
        );
    }

    @CommentAccessVerifiable
    @PutMapping("/{commentId}/{userId}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long commentId,
                                                  @RequestBody @Valid UpsertCommentRequest request) {
        Comment updatedComment = commentService.update(commentMapper.requestToComment(commentId, request));
        return ResponseEntity.ok(
                commentMapper.commentToResponse(updatedComment)
        );
    }

    @CommentAccessVerifiable
    @DeleteMapping("/{commentId}/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
