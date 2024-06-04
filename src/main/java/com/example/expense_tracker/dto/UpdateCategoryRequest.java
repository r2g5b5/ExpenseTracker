package com.example.expense_tracker.dto;

import com.example.expense_tracker.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;

    public static Category toEntity(UpdateCategoryRequest request) {
        return new Category(
                request.getId(),
                request.getName()
        );
    }
}
