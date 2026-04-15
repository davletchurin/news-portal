package com.example.news_portal.web.controller;

import com.example.news_portal.entity.News;
import com.example.news_portal.mapper.v2.NewsMapperV2;
import com.example.news_portal.service.NewsService;
import com.example.news_portal.web.model.*;
import com.example.news_portal.web.model_v2.response.NewsShortResponse;
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
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Tag(name = "News", description = "News API")
public class NewsController {
    private final NewsService newsService;
    private final NewsMapperV2 newsMapper;

    @Operation(
            summary = "Get news",
            operationId = "getNews",
            description = "Get news. Return list of news item with commentsCount"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsListResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter) {
        return ResponseEntity.ok(
                newsMapper.newsListToNewsListResponse(newsService.findAll(filter))
        );
    }

    @Operation(
            summary = "Get news by ID",
            operationId = "getNewsById",
            description = "Get news by ID. Return id, title, description, categoryId, userId, list of comments"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsResponse.class), mediaType = "application/json")
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
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsMapper.newsToResponse(newsService.findById(id))
        );
    }

    @Operation(
            summary = "Create news",
            operationId = "saveNews",
            description = "Create news by title, description, categoryId, userId. Return id, title, description, categoryId, userId"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(schema = @Schema(implementation = NewsShortResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @PostMapping
    public ResponseEntity<NewsShortResponse> create(@Valid @RequestBody UpsertNewsRequest request) {
        News createdNews = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newsMapper.newsToShortResponse(createdNews)
        );
    }

    @Operation(
            summary = "Update news",
            operationId = "updateNews",
            description = "Update news by id, title, description, categoryId, userId. Return id, title, description, categoryId, userId"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsShortResponse.class),
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
    public ResponseEntity<NewsShortResponse> update(@PathVariable Long id,
                                                    @Valid @RequestBody UpsertNewsRequest request) {
        News updatedNews = newsService.update(newsMapper.requestToNews(id, request));
        return ResponseEntity.ok(
                newsMapper.newsToShortResponse(updatedNews)
        );
    }

    @Operation(
            summary = "Delete news by ID",
            operationId = "deleteNewsById",
            description = "Delete news by ID. Return empty body"
    )
    @ApiResponse(
            responseCode = "204",
            content = @Content
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
