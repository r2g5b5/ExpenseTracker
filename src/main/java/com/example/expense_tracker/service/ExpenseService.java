package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.expense.AddExpenseRequest;
import com.example.expense_tracker.dto.expense.GetExpenseResponse;
import com.example.expense_tracker.dto.expense.UpdateExpenseRequest;
import com.example.expense_tracker.wrapper.BaseResult;
import com.example.expense_tracker.wrapper.Result;

import java.util.List;

public interface ExpenseService {
    Result<Long> createExpense(AddExpenseRequest request);
    Result<GetExpenseResponse> getExpenseById(Long id);
    Result<List<GetExpenseResponse>> getAllExpenses();
    BaseResult updateExpense(UpdateExpenseRequest request);
    BaseResult deleteExpense(Long id);
}
