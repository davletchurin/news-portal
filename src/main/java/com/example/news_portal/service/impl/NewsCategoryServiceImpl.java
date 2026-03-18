package com.example.news_portal.service.impl;

import com.example.news_portal.exception.EntityNotFoundException;
import com.example.news_portal.model.NewsCategory;
import com.example.news_portal.repository.NewsCategoryRepository;
import com.example.news_portal.service.NewsCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsCategoryServiceImpl implements NewsCategoryService {
    private final NewsCategoryRepository categoryRepository;
    @Override
    public List<NewsCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public NewsCategory findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("Comment with ID {0} not found", id))
        );
    }

    @Override
    public NewsCategory save(NewsCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public NewsCategory update(NewsCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
