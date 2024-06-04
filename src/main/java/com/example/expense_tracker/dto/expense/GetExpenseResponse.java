package com.example.expense_tracker.dto.expense;

import com.example.expense_tracker.dto.category.GetCategoryResponse;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetExpenseResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private GetCategoryResponse category;

    public static GetExpenseResponse toResponse(Expense expense) {
        return new GetExpenseResponse(
                expense.getId(),
                expense.getAmount(),
                expense.getExpenseDate(),
                GetCategoryResponse.toResponse(expense.getCategory())
        );
    }

    public static List<GetExpenseResponse> toResponse(List<Expense> expenses) {
        return Optional.ofNullable(expenses).orElse(List.of())
                .stream()
                .map(GetExpenseResponse::toResponse)
                .collect(Collectors.toList());
    }

}
