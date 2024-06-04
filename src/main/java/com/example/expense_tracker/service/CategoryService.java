package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.category.AddCategoryRequest;
import com.example.expense_tracker.dto.category.GetCategoryResponse;
import com.example.expense_tracker.dto.category.UpdateCategoryRequest;
import com.example.expense_tracker.wrapper.BaseResult;
import com.example.expense_tracker.wrapper.Result;

import java.util.List;

public interface CategoryService {
    Result<Long> createCategory(AddCategoryRequest request);

    Result<GetCategoryResponse> getCategoryById(Long id);

    Result<List<GetCategoryResponse>> getAllCategories();

    BaseResult updateCategory(UpdateCategoryRequest request);

    BaseResult deleteCategory(Long id);
}
