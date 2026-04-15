package com.example.news_portal.repository;

import com.example.news_portal.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @EntityGraph(attributePaths = {"news", "comments"})
    Optional<User> findById(Long aLong);
}
