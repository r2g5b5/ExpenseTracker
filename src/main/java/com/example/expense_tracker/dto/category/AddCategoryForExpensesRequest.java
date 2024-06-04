package com.example.expense_tracker.dto.category;

import com.example.expense_tracker.dto.expense.AddExpenseRequest;
import com.example.expense_tracker.entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCategoryForExpensesRequest {
    @NotNull(message = "ID is mandatory")
    private Long id;

    public static Category toEntity(AddCategoryForExpensesRequest request) {
        return new Category(request.getId(), null);
    }
}
