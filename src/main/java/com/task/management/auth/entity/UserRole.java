package com.task.management.auth.entity;

public enum UserRole {
    ADMIN("admin"),

    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
