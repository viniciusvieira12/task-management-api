package com.task.management.auth.service;


import com.task.management.auth.dto.request.DeleteRequest;
import com.task.management.auth.dto.request.RegisterRequest;
import com.task.management.auth.dto.request.UpdateRequest;
import com.task.management.auth.dto.response.RegisterResponse;
import com.task.management.auth.dto.response.UpdateResponse;
import com.task.management.auth.dto.response.UserResponse;

public interface UserService {
    RegisterResponse saveUser(RegisterRequest user);
    UserResponse getUser(long id);
    UserResponse getCurrentUser();
    UpdateResponse updateUser(long id, UpdateRequest credentials);
    void deleteUser(long id);
}
