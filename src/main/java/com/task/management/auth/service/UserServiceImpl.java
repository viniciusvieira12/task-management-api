package com.task.management.auth.service;

import com.task.management.auth.dto.request.DeleteRequest;
import com.task.management.auth.dto.request.RegisterRequest;
import com.task.management.auth.dto.request.UpdateRequest;
import com.task.management.auth.dto.response.RegisterResponse;
import com.task.management.auth.dto.response.UpdateResponse;
import com.task.management.auth.entity.User;
import com.task.management.auth.entity.UserRole;
import com.task.management.infra.exception.ConflictException;
import com.task.management.auth.util.UserMapper;
import com.task.management.infra.exception.UnauthorizedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.task.management.auth.dto.response.UserResponse;
import com.task.management.infra.exception.ResourceNotFoundException;
import com.task.management.auth.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper mapUser;
    private PasswordEncoder passwordEncoder;
    private final AuthenticatedUserService authenticatedUserService;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapUser, PasswordEncoder passwordEncoder, AuthenticatedUserService authenticatedUserService) {
        this.userRepository = userRepository;
        this.mapUser = mapUser;
        this.passwordEncoder = passwordEncoder;
        this.authenticatedUserService = authenticatedUserService;
    }

    public RegisterResponse saveUser(RegisterRequest userDto) {
        User user = mapUser.toEntityRegister(userDto);

        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new ConflictException("Username already exists");
        }

        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return mapUser.toResponseRegister(user);
    }

    @Override
    public UserResponse getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return mapUser.toDTO(user.get());
        } else throw new ResourceNotFoundException("User not found");
    }

    @Override
    public UserResponse getCurrentUser() {

        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();

        return mapUser.toDTO(authenticatedUser);
    }

    @Override
    public UpdateResponse updateUser(long id, UpdateRequest userDtoUpt) {

        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!(authenticatedUser.getId() == user.getId())) {
            throw new UnauthorizedException("You cannot update another user");
        }

        mapUser.updateEntityFromDTO(userDtoUpt, user);

        if (userDtoUpt.getPassword() != null &&
                !userDtoUpt.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDtoUpt.getPassword()));
        }

        userRepository.save(user);

        return mapUser.toUpdateResponse(user);
    }

    @Override
    public void deleteUser(long id) {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (authenticatedUser.getId() != user.getId()) {
            throw new UnauthorizedException("You cannot delete another user");
        }

        userRepository.deleteById(user.getId());
    }
}
