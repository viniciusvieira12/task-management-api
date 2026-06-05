package com.task.management.auth.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String username;
    private String email;
    private String role;
}
