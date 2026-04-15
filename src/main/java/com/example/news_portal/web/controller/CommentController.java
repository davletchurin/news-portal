package com.example.news_portal.web.controller;

import com.example.news_portal.entity.Comment;
import com.example.news_portal.mapper.v2.CommentMapperV2;
import com.example.news_portal.service.CommentService;
import com.example.news_portal.web.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Comment API")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapperV2 commentMapper;

    @Operation(
            summary = "Get comments by news ID",
            operationId = "getCommentsByNewsId",
            description = "Get comments by news ID. Return list of comments where userId equals userId in request parameter"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = CommentListResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @GetMapping
    public ResponseEntity<CommentListResponse> findAllByNewsId(@Valid CommentFilter filter) {
        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(commentService.findAllNewsId(filter))
        );
    }

    @Operation(
            summary = "Get comment by ID",
            operationId = "getCommentById",
            description = "Get comment by ID. Return id, description, newsId, userId"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = CommentResponse.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(commentService.findById(id))
        );
    }

    @Operation(
            summary = "Create comment",
            operationId = "saveComment",
            description = "Create comment by description, newsId, userId. Return id, description, newsId, userId"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(schema = @Schema(implementation = CommentResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @PostMapping
    public ResponseEntity<CommentResponse> create(@Valid @RequestBody UpsertCommentRequest request) {
        Comment createdComment = commentService.save(commentMapper.requestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                commentMapper.commentToResponse(createdComment)
        );
    }

    @Operation(
            summary = "Update comment",
            operationId = "updateComment",
            description = "Update comment by id, description, newsId, userId. Return id, description, newsId, userId"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = CommentResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody UpsertCommentRequest request) {
        Comment updatedComment = commentService.update(commentMapper.requestToComment(id, request));
        return ResponseEntity.ok(
                commentMapper.commentToResponse(updatedComment)
        );
    }

    @Operation(
            summary = "Delete comment by ID",
            operationId = "deleteCommentById",
            description = "Delete comment by ID. Return empty body"
    )
    @ApiResponse(
            responseCode = "204",
            content = @Content
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
