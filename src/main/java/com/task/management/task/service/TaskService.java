package com.task.management.task.service;

import com.task.management.auth.entity.User;
import com.task.management.task.dto.request.TaskCreationRequest;
import com.task.management.task.dto.request.TaskUpdateRequest;
import com.task.management.task.dto.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TaskService {
    Page<TaskResponse> getAllTasks(User authenticatedUser, Pageable pageable);

    TaskResponse getTask(long id, User authenticatedUser);

    TaskResponse insertTask(TaskCreationRequest dto, User user);

    TaskResponse updateTask(long id, TaskUpdateRequest dto, User authenticatedUser);

    void deleteTask(long id, User user);
}
