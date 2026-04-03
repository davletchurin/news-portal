package com.example.news_portal.web.controller;

import com.example.news_portal.entity.NewsCategory;
import com.example.news_portal.mapper.NewsCategoryMapper;
import com.example.news_portal.service.NewsCategoryService;
import com.example.news_portal.web.model.NewsCategoryFilter;
import com.example.news_portal.web.model.NewsCategoryListResponse;
import com.example.news_portal.web.model.NewsCategoryResponse;
import com.example.news_portal.web.model.UpsertNewsCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class NewsCategoryController {
    private final NewsCategoryService categoryService;
    private final NewsCategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<NewsCategoryListResponse> findAll(NewsCategoryFilter filter) {
        return ResponseEntity.ok(
                categoryMapper.newsCategoryListToNewsCategoryListResponse(categoryService.findAll(filter))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                categoryMapper.newsCategoryToResponse(categoryService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<NewsCategoryResponse> create(@RequestBody @Valid UpsertNewsCategoryRequest request) {
        NewsCategory createdCategory = categoryService.save(categoryMapper.requestToNewsCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryMapper.newsCategoryToResponse(createdCategory)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> update(@PathVariable Long id,
                                                       @RequestBody @Valid UpsertNewsCategoryRequest request) {
        NewsCategory updatedCategory = categoryService.update(categoryMapper.requestToNewsCategory(id, request));
        return ResponseEntity.ok(
                categoryMapper.newsCategoryToResponse(updatedCategory)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
