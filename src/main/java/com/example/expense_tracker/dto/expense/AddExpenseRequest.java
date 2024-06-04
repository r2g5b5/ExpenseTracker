package com.example.expense_tracker.dto.expense;

import com.example.expense_tracker.dto.category.AddCategoryForExpensesRequest;
import com.example.expense_tracker.dto.category.GetCategoryResponse;
import com.example.expense_tracker.entity.Expense;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddExpenseRequest {
    @NotNull(message = "amount is mandatory")
    @Min(value = 1, message = "Amount must be greater than one")
    private BigDecimal amount;
    @NotNull(message = "expenseDate is mandatory")
    private LocalDate expenseDate;
    @NotNull(message = "category is mandatory")
    @Valid
    private AddCategoryForExpensesRequest category;

    public static Expense toEntity(AddExpenseRequest request) {
        return new Expense(
                null,
                request.getAmount(),
                request.getExpenseDate(),
                AddCategoryForExpensesRequest.toEntity(request.getCategory())
        );
    }
}
