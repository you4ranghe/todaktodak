package com.bonfire.web.dto;

import com.bonfire.domain.Trash;

import java.time.LocalDateTime;

public record TrashResponse(
        Long id,
        String content,
        String category,
        String categoryLabel,
        String aiConsolation,
        String status,
        LocalDateTime createdAt
) {
    public static TrashResponse from(Trash t) {
        return new TrashResponse(
                t.getId(),
                t.getContent(),
                t.getCategory().name(),
                t.getCategory().getLabel(),
                t.getAiConsolation(),
                t.getStatus().name(),
                t.getCreatedAt()
        );
    }
}
