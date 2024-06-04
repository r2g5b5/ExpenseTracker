package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.AddCategoryRequest;
import com.example.expense_tracker.wrapper.Result;

public interface CategoryService {
    Result<Long> createCategory(AddCategoryRequest request);
}
