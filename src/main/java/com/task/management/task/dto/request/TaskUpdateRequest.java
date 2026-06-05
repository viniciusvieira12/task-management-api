package com.task.management.task.dto.request;

import com.task.management.task.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskUpdateRequest {
    private long id;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    private String description;

    private Task.Status status;
}
