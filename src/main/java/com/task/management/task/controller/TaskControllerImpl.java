package com.task.management.task.controller;

import com.task.management.auth.entity.User;
import com.task.management.auth.service.AuthenticatedUserService;
import com.task.management.task.dto.request.TaskCreationRequest;
import com.task.management.task.dto.request.TaskUpdateRequest;
import com.task.management.task.dto.response.TaskResponse;
import com.task.management.task.service.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskControllerImpl {

    TaskServiceImpl serviceTasks;
    AuthenticatedUserService authenticatedUserService;

    public TaskControllerImpl(TaskServiceImpl serviceTasks, AuthenticatedUserService authenticatedUserService) {
        this.serviceTasks = serviceTasks;
        this.authenticatedUserService = authenticatedUserService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> listTasks(Pageable pageable) {
        //pega o usuario atual através do securityContext, cujo foi adicionado pelo filtro (SecurityFilter) de altenticação dessa requisição
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        return ResponseEntity.ok(serviceTasks.getAllTasks(authenticatedUser, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable long id) {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        return ResponseEntity.ok(serviceTasks.getTask(id, authenticatedUser));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskCreationRequest request) {
        //pega o usuario atual através do securityContext, cujo foi adicionado pelo filtro (SecurityFilter) de altenticação dessa requisição
        User user = authenticatedUserService.getAuthenticatedUser();
        return ResponseEntity.ok(serviceTasks.insertTask(request, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable long id, @RequestBody @Valid TaskUpdateRequest taskDto) {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        return ResponseEntity.ok(serviceTasks.updateTask(id, taskDto, authenticatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        serviceTasks.deleteTask(id, authenticatedUser);
        return ResponseEntity.ok().build();
    }
}
