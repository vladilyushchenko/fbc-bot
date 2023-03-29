package com.fbc.bot.user.service;

import com.fbc.bot.telegram.dto.UserCacheDto;

import java.util.Optional;

public interface UserCacheService {

    <T> Optional<UserCacheDto<T>> findByUserId(Long userId, Class<T> aClass);

    <T> Optional<UserCacheDto<T>> findByTelegramUserId(Long teleramId, Class<T> aClass);

    void save(UserCacheDto<?> userCacheDto);

    void removeByUserId(Long userId);
}