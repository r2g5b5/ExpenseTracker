package com.example.expense_tracker.utils;

import com.example.expense_tracker.wrapper.Error;
import com.example.expense_tracker.wrapper.ErrorCodes;
import com.example.expense_tracker.wrapper.Result;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidationUtils {

    public static <T> Result<T> handleValidationErrors(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return new Result<>(new Error(errors + "", ErrorCodes.BAD_REQUEST, "BAD_REQUEST"));
        }
        return null;
    }

}
