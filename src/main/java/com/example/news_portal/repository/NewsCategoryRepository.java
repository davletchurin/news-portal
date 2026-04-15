package com.example.news_portal.repository;

import com.example.news_portal.entity.NewsCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
    @Override
    @EntityGraph(attributePaths = {"news"})
    Optional<NewsCategory> findById(Long aLong);
}
