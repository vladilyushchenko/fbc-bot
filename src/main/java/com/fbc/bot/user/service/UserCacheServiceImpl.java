package com.fbc.bot.user.service;

import com.fbc.bot.telegram.dto.UserCacheDto;
import com.fbc.bot.user.model.User;
import com.fbc.bot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserCacheServiceImpl implements UserCacheService {

    private final ConcurrentHashMap<Long, UserCacheDto<?>> userCacheMap = new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    @Override
    public <T> Optional<UserCacheDto<T>> findByUserId(Long userId, Class<T> aClass) {
        return Optional.ofNullable((UserCacheDto<T>) userCacheMap.get(userId));
    }

    @Override
    public <T> Optional<UserCacheDto<T>> findByTelegramUserId(Long telegramId, Class<T> aClass) {
        var userId = userRepository.findByTelegramId(telegramId)
                .map(User::getId);
        return userId.map(id -> (UserCacheDto<T>) userCacheMap.get(id));
    }

    @Override
    public void save(UserCacheDto<?> userCacheDto) {
        userCacheMap.put(userCacheDto.getUserId(), userCacheDto);
    }

    @Override
    public void removeByUserId(Long userId) {
        userCacheMap.remove(userId);
    }
}