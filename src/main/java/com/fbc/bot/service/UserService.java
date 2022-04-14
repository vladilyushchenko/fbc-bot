package com.fbc.bot.service;

import com.fbc.bot.dto.UserDto;
import com.fbc.bot.model.User;

import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto userDto);

    User createUser(org.telegram.telegrambots.meta.api.objects.User tgUser);

    UserDto getUserById(Long id);

    Optional<User> getUserByTelegramId(Integer telegramId);
}