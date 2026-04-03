package com.example.news_portal.service;

import com.example.news_portal.entity.User;
import com.example.news_portal.web.model.UserFilter;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> findAll(UserFilter filter);
    User findById(Long id);
    User save(User user);
    User update(User user);
    void deleteById(Long id);
}
