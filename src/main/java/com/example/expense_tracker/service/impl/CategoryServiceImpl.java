package com.example.expense_tracker.service.impl;

import com.example.expense_tracker.dto.AddCategoryRequest;
import com.example.expense_tracker.dto.GetCategoryResponse;
import com.example.expense_tracker.dto.UpdateCategoryRequest;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.wrapper.BaseResult;
import com.example.expense_tracker.wrapper.Error;
import com.example.expense_tracker.wrapper.ErrorCodes;
import com.example.expense_tracker.wrapper.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    @Transactional
    public Result<Long> createCategory(AddCategoryRequest request) {
        Category category = repository.save(AddCategoryRequest.toEntity(request));
        return new Result<>(category.getId());
    }

    @Override
    public Result<GetCategoryResponse> getCategoryById(Long id) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return new Result<>(GetCategoryResponse.toResponse(category));
        }
        return new Result<>(new Error("cant find category with id: " + id, ErrorCodes.NOT_FOUND, "categoryId"));
    }

    @Override
    public Result<List<GetCategoryResponse>> getAllCategories() {
        return new Result<>(GetCategoryResponse.toResponse(repository.findAll()));
    }

    @Override
    @Transactional
    public BaseResult updateCategory(UpdateCategoryRequest request) {
        Optional<Category> optionalCategory = repository.findById(request.getId());
        if (optionalCategory.isPresent()) {
            repository.save(UpdateCategoryRequest.toEntity(request));
            return new BaseResult();
        }
        return new BaseResult(new Error("cant update category with id: " + request.getId(), ErrorCodes.NOT_FOUND, "categoryId"));
    }

    @Override
    @Transactional
    public BaseResult deleteCategory(Long id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            repository.deleteById(id);
            return new BaseResult();
        }
        return new BaseResult(new Error("cant delete category with id: " + id, ErrorCodes.NOT_FOUND, "categoryId"));

    }
}
