package com.example.news_portal.web.controller;

import com.example.news_portal.entity.User;
import com.example.news_portal.mapper.UserMapper;
import com.example.news_portal.service.UserService;
import com.example.news_portal.web.model.UpsertUserRequest;
import com.example.news_portal.web.model.UserListResponse;
import com.example.news_portal.web.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(
                userMapper.userListToUserListResponse(userService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User createdUser = userService.save(userMapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userMapper.userToResponse(createdUser)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid UpsertUserRequest request) {
        User updatedUser = userService.update(userMapper.requestToUser(id, request));
        return ResponseEntity.ok(
                userMapper.userToResponse(updatedUser)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
