package com.example.expense_tracker.dto.category;

import com.example.expense_tracker.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponse {
    @NotNull(message = "ID is mandatory")
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    public static GetCategoryResponse toResponse(Category category) {
        return new GetCategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    public static Category toEntity(GetCategoryResponse category) {
        return new Category(
                category.getId(),
                category.getName()
        );
    }

    public static List<GetCategoryResponse> toResponse(List<Category> categories) {
        return Optional.ofNullable(categories).orElse(List.of())
                .stream()
                .map(GetCategoryResponse::toResponse)
                .collect(Collectors.toList());
    }

}