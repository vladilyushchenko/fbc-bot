package com.fbc.bot.user.service;

import com.fbc.bot.exception.EntityNotFoundException;
import com.fbc.bot.user.dto.UserDto;
import com.fbc.bot.user.mapper.UserMapper;
import com.fbc.bot.user.model.User;
import com.fbc.bot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.fbc.bot.user.model.UserStatus.UNKNOWN;
import static com.fbc.bot.user.model.User_.TELEGRAM_ID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = mapper.toEntity(userDto);
        return mapper.toDto(repository.save(user));
    }

    @Transactional
    public User createUser(org.telegram.telegrambots.meta.api.objects.User tgUser) {
        User user = mapper.toEntity(tgUser);
        user.setUserStatus(UNKNOWN);
        return repository.save(user);
    }

    @Transactional
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