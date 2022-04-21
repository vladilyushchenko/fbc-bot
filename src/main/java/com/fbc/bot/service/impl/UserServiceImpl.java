package com.fbc.bot.service.impl;

import com.fbc.bot.dto.UserDto;
import com.fbc.bot.exception.EntityNotFoundException;
import com.fbc.bot.model.User;
import com.fbc.bot.repository.UserRepository;
import com.fbc.bot.service.UserService;
import com.fbc.bot.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fbc.bot.model.UserStatus.UNKNOWN;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.toEntity(userDto);
        return mapper.toDto(repository.save(user));
    }

    @Override
    public User createUser(org.telegram.telegrambots.meta.api.objects.User tgUser) {
        User user = mapper.toEntity(tgUser);
        user.setUserStatus(UNKNOWN);
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        return mapper.toDto(user);
    }

    @Override
    public Optional<User> getUserByTelegramId(Integer telegramId) {
        return repository.findByTelegramId(telegramId);
    }
}