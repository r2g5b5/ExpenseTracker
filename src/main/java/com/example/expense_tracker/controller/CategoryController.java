package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.AddCategoryRequest;
import com.example.expense_tracker.dto.GetCategoryResponse;
import com.example.expense_tracker.dto.UpdateCategoryRequest;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.utils.ValidationUtils;
import com.example.expense_tracker.wrapper.BaseResult;
import com.example.expense_tracker.wrapper.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/createCategory")
    public Result<Long> createCategory(@RequestBody @Valid AddCategoryRequest request,
                                       BindingResult result) {
        Result<Long> validationResult = ValidationUtils.handleValidationErrors(result);
        if (validationResult != null) {
            return validationResult;
        }
        return service.createCategory(request);
    }

    @GetMapping("/getById")
    public Result<GetCategoryResponse> getCategoryById(Long id) {
        return service.getCategoryById(id);
    }

    @GetMapping("/getAll")
    public Result<List<GetCategoryResponse>> getCategories() {
        return service.getAllCategories();
    }

    @PutMapping("/updateCategory")
    public BaseResult updateCategory(@RequestBody @Valid UpdateCategoryRequest request, BindingResult bindingResult) {
        Result<String> validationErrors = ValidationUtils.handleValidationErrors(bindingResult);
        if (validationErrors != null) {
            return validationErrors;
        }
        return service.updateCategory(request);
    }

    @DeleteMapping("/deleteCategory")
    public BaseResult deleteCategory(Long id) {
        return service.deleteCategory(id);
    }

}
