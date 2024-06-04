package com.example.expense_tracker.service.impl;

import com.example.expense_tracker.dto.category.GetCategoryResponse;
import com.example.expense_tracker.dto.expense.AddExpenseRequest;
import com.example.expense_tracker.dto.expense.GetExpenseResponse;
import com.example.expense_tracker.dto.expense.UpdateExpenseRequest;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.repository.ExpenseRepository;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.ExpenseService;
import com.example.expense_tracker.wrapper.BaseResult;
import com.example.expense_tracker.wrapper.Error;
import com.example.expense_tracker.wrapper.ErrorCodes;
import com.example.expense_tracker.wrapper.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;


    @Override
    public Result<Long> createExpense(AddExpenseRequest request) {
        Result<GetCategoryResponse> categoryResult = categoryService.getCategoryById(request.getCategory().getId());
        if (!categoryResult.isSuccess) {
            return new Result<>(new Error("cant find category with id: " + request.getCategory().getId(), ErrorCodes.NOT_FOUND, "categoryID"));
        }
        Expense expense = expenseRepository.save(AddExpenseRequest.toEntity(request));
        return new Result<>(expense.getId());
    }

    @Override
    public Result<GetExpenseResponse> getExpenseById(Long id) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        return expenseOptional.map(expense -> new Result<>(GetExpenseResponse.toResponse(expense)))
                .orElseGet(() -> new Result<>(new Error("cant find expense with id: " + id, ErrorCodes.NOT_FOUND, "expenseId")));
    }

    @Override
    public Result<List<GetExpenseResponse>> getAllExpenses() {
        return new Result<>(GetExpenseResponse.toResponse(expenseRepository.findAll()));
    }

    @Override
    public BaseResult updateExpense(UpdateExpenseRequest request) {
        Result<GetCategoryResponse> categoryResult = categoryService.getCategoryById(request.getCategory().getId());
        Optional<Expense> expenseOptional = expenseRepository.findById(request.getId());
        if (!categoryResult.isSuccess) {
            return new Result<>(new Error("cant find category with id: " + request.getCategory().getId(), ErrorCodes.NOT_FOUND, "categoryID"));
        }
        if (expenseOptional.isPresent()) {
            expenseRepository.save(UpdateExpenseRequest.toEntity(request));
            return new BaseResult();
        }
        return new Result<>(new Error("cant find expense with id: " + request.getId(), ErrorCodes.NOT_FOUND, "expenseId"));
    }

    @Override
    public BaseResult deleteExpense(Long id) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        if (expenseOptional.isPresent()) {
            expenseRepository.deleteById(id);
            return new BaseResult();
        }
        return new Result<>(new Error("cant find expense with id: " + id, ErrorCodes.NOT_FOUND, "expenseId"));
    }
}
