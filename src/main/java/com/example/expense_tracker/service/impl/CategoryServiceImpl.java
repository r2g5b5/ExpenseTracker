package com.example.expense_tracker.service.impl;

import com.example.expense_tracker.dto.AddCategoryRequest;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.mapper.CategoryMapper;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.wrapper.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    @Transactional
    public Result<Long> createCategory(AddCategoryRequest request) {
        Category category = repository.save(CategoryMapper.mapToCategory(request));
        return new Result<>(category.getId());
    }
}
