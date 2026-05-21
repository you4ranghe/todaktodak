package com.bonfire.web.dto;

import com.bonfire.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ThrowAwayRequest(
        @NotBlank(message = "버릴 감정을 적어주세요.")
        @Size(max = 5000, message = "5000자 이내로 적어주세요.")
        String content,

        @NotNull(message = "카테고리를 선택해주세요.")
        Category category
) {
}
