package com.task.management.task.util;

import com.task.management.task.dto.request.TaskCreationRequest;
import com.task.management.task.dto.request.TaskUpdateRequest;
import com.task.management.task.dto.response.TaskResponse;
import com.task.management.task.entity.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskCreationRequest dto);

    TaskResponse toResponse(Task task);

    TaskUpdateRequest toDTO(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateTaskFromDto(TaskUpdateRequest dto, @MappingTarget Task entity);
}
