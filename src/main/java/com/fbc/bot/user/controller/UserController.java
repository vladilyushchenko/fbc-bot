package com.fbc.bot.user.controller;

import com.fbc.bot.user.service.UserService;
import com.fbc.bot.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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