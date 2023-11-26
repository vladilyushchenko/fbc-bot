package com.fbc.bot.user.controller;

import com.fbc.bot.user.dto.UserDto;
import com.fbc.bot.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable @Valid @NotNull Long id) {
        return service.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return service.createUser(userDto);
    }
}