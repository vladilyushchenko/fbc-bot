package com.fbc.bot.service;

import com.fbc.bot.dto.UserDto;
import com.fbc.bot.exception.EntityNotFoundException;
import com.fbc.bot.model.User;
import com.fbc.bot.repository.UserRepository;
import com.fbc.bot.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.fbc.bot.model.UserStatus.UNKNOWN;
import static com.fbc.bot.model.User_.TELEGRAM_ID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDto createUser(UserDto userDto) {
        User user = mapper.toEntity(userDto);
        return mapper.toDto(repository.save(user));
    }

    public User createUser(org.telegram.telegrambots.meta.api.objects.User tgUser) {
        User user = mapper.toEntity(tgUser);
        user.setUserStatus(UNKNOWN);
        return repository.save(user);
    }

    public UserDto updateUser(User user) {
        return mapper.toDto(repository.save(user));
    }

    public UserDto getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
        return mapper.toDto(user);
    }

    public User getUserByTelegramId(Long telegramId) {
        return repository.findByTelegramId(telegramId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, TELEGRAM_ID, telegramId));
    }
}