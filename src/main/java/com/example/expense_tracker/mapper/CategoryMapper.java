package com.example.expense_tracker.mapper;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.entity.Category;

public class CategoryMapper {

    public static Category mapToCategory(CategoryDto dto) {
        return new Category(
                dto.id(),
                dto.name()
        );
    }

    public static CategoryDto mapToCategory(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

}
