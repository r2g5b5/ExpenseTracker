package com.example.expense_tracker.mapper;

import com.example.expense_tracker.dto.AddCategoryRequest;
import com.example.expense_tracker.entity.Category;

public class CategoryMapper {

    public static Category mapToCategory(AddCategoryRequest request) {
        return new Category(
                null,
                request.name()
        );
    }

    public static AddCategoryRequest mapToAddCategoryRequest(Category category) {
        return new AddCategoryRequest(
                category.getName()
        );
    }

}
