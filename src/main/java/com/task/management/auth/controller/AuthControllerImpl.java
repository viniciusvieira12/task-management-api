package com.task.management.auth.controller;

import com.task.management.auth.dto.request.DeleteRequest;
import com.task.management.auth.dto.request.LoginRequest;
import com.task.management.auth.dto.request.RegisterRequest;
import com.task.management.auth.dto.request.UpdateRequest;
import com.task.management.auth.dto.response.LoginResponse;
import com.task.management.auth.dto.response.RegisterResponse;
import com.task.management.auth.dto.response.UpdateResponse;
import com.task.management.auth.dto.response.UserResponse;
import com.task.management.auth.entity.User;
import com.task.management.auth.service.UserService;
import com.task.management.infra.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthControllerImpl{

    UserService userService;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public AuthControllerImpl(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }


    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse> updateUser(@PathVariable int id, @RequestBody @Valid UpdateRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
        // returns 200... even without a body, this indicates that the operation was successful
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginCredentials) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginCredentials.getUsername(),
                loginCredentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token, user.getId(), user.getUsername()));
    }
}
