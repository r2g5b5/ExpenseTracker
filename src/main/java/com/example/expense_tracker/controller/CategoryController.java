package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.category.AddCategoryRequest;
import com.example.expense_tracker.dto.category.GetCategoryResponse;
import com.example.expense_tracker.dto.category.UpdateCategoryRequest;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.utils.ValidationUtils;
import com.example.expense_tracker.wrapper.BaseResult;
import com.example.expense_tracker.wrapper.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid AddCategoryRequest request,
                               BindingResult result) {
        Result<Long> validationResult = ValidationUtils.handleValidationErrors(result);
        if (validationResult != null) {
            return validationResult;
        }
        return service.createCategory(request);
    }

    @GetMapping("/getById")
    public Result<GetCategoryResponse> getById(Long id) {
        return service.getCategoryById(id);
    }

    @GetMapping("/getAll")
    public Result<List<GetCategoryResponse>> getAll() {
        return service.getAllCategories();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody @Valid UpdateCategoryRequest request, BindingResult bindingResult) {
        Result<String> validationErrors = ValidationUtils.handleValidationErrors(bindingResult);
        if (validationErrors != null) {
            return validationErrors;
        }
        return service.updateCategory(request);
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long id) {
        return service.deleteCategory(id);
    }



}
