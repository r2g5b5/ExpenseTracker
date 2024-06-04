package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.AddCategoryRequest;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.utils.ValidationUtils;
import com.example.expense_tracker.wrapper.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
