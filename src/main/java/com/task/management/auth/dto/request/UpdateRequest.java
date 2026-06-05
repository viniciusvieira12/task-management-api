package com.task.management.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRequest {
    private String username;
    @Email
    private String email;
    private String password;
}
