package com.task.management.infra.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt.security.token")
public record JwtProperties(
        String secretKey,
        long expiration
) {}
