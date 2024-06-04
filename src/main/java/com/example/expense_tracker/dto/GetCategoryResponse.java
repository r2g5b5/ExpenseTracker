package com.example.expense_tracker.dto;

import com.example.expense_tracker.entity.Category;
import lombok.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponse {
    private Long id;
    private String name;

    public static GetCategoryResponse toResponse(Category response) {
        return new GetCategoryResponse(
                response.getId(),
                response.getName()
        );
    }

    public static List<GetCategoryResponse> toResponse(List<Category> categories) {
        return Optional.ofNullable(categories).orElse(List.of())
                .stream()
                .map(GetCategoryResponse::toResponse)
                .collect(Collectors.toList());
    }

}