package com.task.management.task.dto.response;

import com.task.management.task.entity.Task;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private long id;
    private String name;
    private String description;
    private Task.Status status;
    private LocalDateTime createdDate;
}
