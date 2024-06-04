package com.example.expense_tracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddCategoryRequest(
        @NotNull
        @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
        String name) {
}
