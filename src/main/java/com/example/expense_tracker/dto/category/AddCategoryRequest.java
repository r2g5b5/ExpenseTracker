package com.example.expense_tracker.dto.category;

import com.example.expense_tracker.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddCategoryRequest {
    @NotNull
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    String name;

    public static Category toEntity(AddCategoryRequest request) {
        return new Category(null,
                request.getName());
    }
}
