package com.task.management.infra.exception;

import java.time.LocalDateTime;

public record ErrorGlobalException(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {}
