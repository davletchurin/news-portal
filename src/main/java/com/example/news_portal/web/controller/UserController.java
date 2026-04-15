package com.example.news_portal.web.controller;

import com.example.news_portal.entity.User;
import com.example.news_portal.mapper.v2.UserMapperV2;
import com.example.news_portal.service.UserService;
import com.example.news_portal.web.model.*;
import com.example.news_portal.web.model_v2.list_response.UserListShortResponse;
import com.example.news_portal.web.model_v2.response.UserShortResponse;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserService userService;
    private final UserMapperV2 userMapper;

    @Operation(
            summary = "Get users",
            operationId = "getUsers",
            description = "Get users. Return list of users"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserListShortResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @GetMapping
    public ResponseEntity<UserListShortResponse> findAll(@Valid UserFilter filter) {
        return ResponseEntity.ok(
                userMapper.userListToUserListShortResponse(userService.findAll(filter))
        );
    }

    @Operation(
            summary = "Get user by ID",
            operationId = "getUserById",
            description = "Get user by ID. Return id, userName, list of news, list of comments"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")
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
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @Operation(
            summary = "Create user",
            operationId = "saveUser",
            description = "Create user by userName. Return id, userName"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(schema = @Schema(implementation = UserShortResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(responseCode = "400", ref = "BadRequest")
    })
    @PostMapping()
    public ResponseEntity<UserShortResponse> create(@Valid @RequestBody UpsertUserRequest request) {
        User createdUser = userService.save(userMapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userMapper.userToShortResponse(createdUser)
        );
    }

    @Operation(
            summary = "Update user",
            operationId = "updateUser",
            description = "Update user by id, userName. Return id, userName"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserShortResponse.class),
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
    public ResponseEntity<UserShortResponse> update(@PathVariable Long id,
                                                    @Valid @RequestBody UpsertUserRequest request) {
        User updatedUser = userService.update(userMapper.requestToUser(id, request));
        return ResponseEntity.ok(
                userMapper.userToShortResponse(updatedUser)
        );
    }

    @Operation(
            summary = "Delete user by ID",
            operationId = "deleteUserById",
            description = "Delete user by ID. Return empty body"
    )
    @ApiResponse(
            responseCode = "204",
            content = @Content
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
