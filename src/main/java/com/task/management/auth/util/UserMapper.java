package com.task.management.auth.util;

import com.task.management.auth.dto.request.RegisterRequest;
import com.task.management.auth.dto.request.UpdateRequest;
import com.task.management.auth.dto.response.RegisterResponse;
import com.task.management.auth.dto.response.UpdateResponse;
import com.task.management.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.task.management.auth.dto.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntityRegister(RegisterRequest dto);

    RegisterResponse toResponseRegister(User user);

    void updateEntityFromDTO(UpdateRequest dto, @MappingTarget User entity);

    UpdateResponse toUpdateResponse(User user);

    UserResponse toDTO(User entity);

}
