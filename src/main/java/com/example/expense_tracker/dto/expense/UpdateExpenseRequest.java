package com.example.expense_tracker.dto.expense;

import com.example.expense_tracker.dto.category.AddCategoryForExpensesRequest;
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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExpenseRequest {
    @NotNull(message = "id is mandatory")
    private Long id;
    @NotNull(message = "amount is mandatory")
    @Min(value = 1, message = "Amount must be greater than one")
    private BigDecimal amount;
    @NotNull(message = "expenseDate is mandatory")
    private LocalDate expenseDate;
    @NotNull(message = "category is mandatory")
    @Valid
    private AddCategoryForExpensesRequest category;

    public static Expense toEntity(UpdateExpenseRequest request) {
        return new Expense(
                request.getId(),
                request.getAmount(),
                request.getExpenseDate(),
                AddCategoryForExpensesRequest.toEntity(request.getCategory())
        );
    }
}
