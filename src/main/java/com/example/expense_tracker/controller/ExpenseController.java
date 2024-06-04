package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.expense.AddExpenseRequest;
import com.example.expense_tracker.dto.expense.GetExpenseResponse;
import com.example.expense_tracker.dto.expense.UpdateExpenseRequest;
import com.example.expense_tracker.service.ExpenseService;
import com.example.expense_tracker.utils.ValidationUtils;
import com.example.expense_tracker.wrapper.BaseResult;
import com.example.expense_tracker.wrapper.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Expense")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    @PostMapping("/create")
    public Result<Long> create(@RequestBody @Valid AddExpenseRequest request, BindingResult bindingResult) {
        Result<Long> validation = ValidationUtils.handleValidationErrors(bindingResult);
        if (validation != null) {
            return validation;
        }
        return service.createExpense(request);
    }

    @GetMapping("/getById")
    public Result<GetExpenseResponse> getById(Long id) {
        return service.getExpenseById(id);
    }

    @GetMapping("/getAll")
    public Result<List<GetExpenseResponse>> getAll() {
        return service.getAllExpenses();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody @Valid UpdateExpenseRequest request, BindingResult bindingResult) {
        Result<String> validation = ValidationUtils.handleValidationErrors(bindingResult);
        if (validation != null) {
            return validation;
        }
        return service.updateExpense(request);
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long id) {
        return service.deleteExpense(id);
    }

}
