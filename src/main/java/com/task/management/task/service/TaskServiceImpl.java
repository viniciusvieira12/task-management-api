package com.task.management.task.service;

import com.task.management.auth.entity.User;
import com.task.management.auth.repository.UserRepository;
import com.task.management.task.dto.request.TaskCreationRequest;
import com.task.management.task.dto.request.TaskUpdateRequest;
import com.task.management.task.dto.response.TaskResponse;
import com.task.management.task.entity.Task;
import com.task.management.infra.exception.ResourceNotFoundException;
import com.task.management.task.repository.TaskRepository;
import com.task.management.task.util.TaskMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Page<TaskResponse> getAllTasks(User user, Pageable pageable) {
//        Iterable<Task> tasks = taskRepository.findAll();
//                return StreamSupport.stream(tasks.spliterator(), false)
//                        .map(task -> taskMapper.toDTO(task))
//                        .filter((taskDTO -> taskDTO.getName().length() > 40))
//                        .toList();

//        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
//                .map(taskMapper::toDTO)
//                .filter(taskDTO->taskDTO.getDescription().equalsIgnoreCase("a"))
//                .reduce(0, (total, taskDTO) -> total + taskDTO.getDescription().length(), Integer::sum)
//                .toList();

        Page<Task> tasks = taskRepository.findByUser(user, pageable);


        return tasks.map(taskMapper::toResponse);
    }

    @Override
    public TaskResponse getTask(long id, User authenticatedUser) {
        Task task = taskRepository.findByIdAndUser(id, authenticatedUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        return taskMapper.toResponse(task);
    }

    @Override
    public TaskResponse insertTask(TaskCreationRequest dto, User user) {
        Task task = taskMapper.toEntity(dto);
        task.setUser(user);
        taskRepository.save(task);
        return taskMapper.toResponse(task);
    }

    @Override
    public TaskResponse updateTask(long id, TaskUpdateRequest dto, User authenticatedUser) {
        Task task = taskRepository.findByIdAndUser(id, authenticatedUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        if (!task.getDescription().equalsIgnoreCase(dto.getDescription())) {
            task.setDescription(dto.getDescription());
        }

        if (dto.getStatus() != null && !task.getStatus().equals(dto.getStatus())) {
            task.setStatus(dto.getStatus());
        }

        taskRepository.save(task);
        return taskMapper.toResponse(task);
    }

    @Override
    public void deleteTask(long id, User authenticatedUser) {
        Task task = taskRepository
                .findByIdAndUser(id, authenticatedUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));
        taskRepository.deleteById(id);
    }
}
