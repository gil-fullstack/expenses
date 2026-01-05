package com.hightech.expenses.controller;

import com.hightech.expenses.domain.User;
import com.hightech.expenses.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User.UserResponse create(@RequestBody @Valid User.UserRequest req) {
        return userService.toResponse(userService.create(req));
    }

    @GetMapping
    public List<User.UserResponse> list() {
        return userService.list().stream().map(userService::toResponse).toList();
    }

    @GetMapping("/{id}")
    public User.UserResponse get(@PathVariable Long id) {
        return userService.toResponse(userService.get(id));
    }

    @PutMapping("/{id}")
    public User.UserResponse update(@PathVariable Long id, @RequestBody @Valid User.UserRequest req) {
        return userService.toResponse(userService.update(id, req));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
