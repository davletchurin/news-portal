package com.example.news_portal.repository;

import com.example.news_portal.entity.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {
    @Override
    @EntityGraph(attributePaths = {"comments"})
    Optional<News> findById(Long aLong);
}
