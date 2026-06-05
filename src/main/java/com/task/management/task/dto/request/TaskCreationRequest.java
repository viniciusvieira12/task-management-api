    package com.task.management.task.dto.request;

    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import lombok.Data;

    @Data
    public class TaskCreationRequest {
        @NotBlank
        @Size(max = 100)
        private String name;
        @NotBlank
        private String description;
    }
