package io.github.anaclaraoliv.bullet_journal_backend.dto.request;

public record TaskPositionUpdateRequest(
        String id,
        Integer position
) {}