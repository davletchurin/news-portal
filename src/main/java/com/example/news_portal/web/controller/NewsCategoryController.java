package com.example.news_portal.web.controller;

import com.example.news_portal.entity.NewsCategory;
import com.example.news_portal.mapper.v2.NewsCategoryMapperV2;
import com.example.news_portal.service.NewsCategoryService;
import com.example.news_portal.web.model.*;
import com.example.news_portal.web.model_v2.list_response.NewsCategoryListShortResponse;
import com.example.news_portal.web.model_v2.response.NewsCategoryShortResponse;
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
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class NewsCategoryController {
    private final NewsCategoryService categoryService;
    private final NewsCategoryMapperV2 categoryMapper;

    @Operation(
            summary = "Get categories",
            operationId = "getCategories",
            description = "Get categories. Return list of categories"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsCategoryListShortResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @GetMapping
    public ResponseEntity<NewsCategoryListShortResponse> findAll(@Valid NewsCategoryFilter filter) {
        return ResponseEntity.ok(
                categoryMapper.newsCategoryListToNewsCategoryListShortResponse(categoryService.findAll(filter))
        );
    }

    @Operation(
            summary = "Get category by ID",
            operationId = "getCategoryById",
            description = "Get category by ID. Return id, categoryName, list of news"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsCategoryResponse.class), mediaType = "application/json")
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
    public ResponseEntity<NewsCategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                categoryMapper.newsCategoryToResponse(categoryService.findById(id))
        );
    }

    @Operation(
            summary = "Create category",
            operationId = "saveCategory",
            description = "Create category by categoryName. Return id, categoryName"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(schema = @Schema(implementation = NewsCategoryShortResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @PostMapping
    public ResponseEntity<NewsCategoryShortResponse> create(@Valid @RequestBody UpsertNewsCategoryRequest request) {
        NewsCategory createdCategory = categoryService.save(categoryMapper.requestToNewsCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryMapper.newsCategoryToShortResponse(createdCategory)
        );
    }

    @Operation(
            summary = "Update category",
            operationId = "updateCategory",
            description = "Update category by id, categoryName. Return id, categoryName"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsCategoryShortResponse.class),
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
    public ResponseEntity<NewsCategoryShortResponse> update(@PathVariable Long id,
                                                            @Valid @RequestBody UpsertNewsCategoryRequest request) {
        NewsCategory updatedCategory = categoryService.update(categoryMapper.requestToNewsCategory(id, request));
        return ResponseEntity.ok(
                categoryMapper.newsCategoryToShortResponse(updatedCategory)
        );
    }

    @Operation(
            summary = "Delete category by ID",
            operationId = "deleteCategoryById",
            description = "Delete category by ID. Return empty body"
    )
    @ApiResponse(
            responseCode = "204",
            content = @Content
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
