package com.example.news_portal.web.controller;

import com.example.news_portal.entity.News;
import com.example.news_portal.mapper.NewsMapper;
import com.example.news_portal.service.NewsService;
import com.example.news_portal.web.model.NewsListResponse;
import com.example.news_portal.web.model.NewsResponse;
import com.example.news_portal.web.model.UpsertNewsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll() {
        return ResponseEntity.ok(
                newsMapper.newsListToNewsListResponse(newsService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsMapper.newsToResponse(newsService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request) {
        News createdNews = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newsMapper.newsToResponse(createdNews)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid UpsertNewsRequest request) {
        News updatedNews = newsService.update(newsMapper.requestToNews(id, request));
        return ResponseEntity.ok(
                newsMapper.newsToResponse(updatedNews)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
