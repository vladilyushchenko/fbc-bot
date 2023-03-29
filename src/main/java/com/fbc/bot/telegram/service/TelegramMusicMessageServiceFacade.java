package com.fbc.bot.telegram.service;

import com.fbc.bot.user.repository.UserRepository;
import com.fbc.bot.user.service.UserCacheService;
import com.fbc.bot.user.service.UserService;
import com.fbc.bot.music.dto.CottageMusicResultDto;
import com.fbc.bot.music.dto.CottageMusicResultDto.CreateMusicStatus;
import com.fbc.bot.music.service.MusicService;
import com.fbc.bot.telegram.dto.UserCacheDto;
import com.fbc.bot.telegram.dto.UserCacheStatus;
import com.fbc.bot.telegram.handler.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.fbc.bot.music.dto.CottageMusicResultDto.CreateMusicStatus.WRITE_TITLE_AWAITING;
import static com.fbc.bot.telegram.dto.UserCacheStatus.ADDING_MUSIC;
import static com.fbc.bot.telegram.handler.input.MessageType.ADD_MUSIC;

@Service
@RequiredArgsConstructor
public class TelegramMusicMessageServiceFacade {

    private final MusicService musicService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserCacheService userCacheService;

    @Transactional
    public CottageMusicResultDto create(String musicQuery, User tgUser) {
        var internalUser = userRepository.findByTelegramId(tgUser.getId())
                .orElseGet(() -> userService.createUser(tgUser));
        var currentCache = userCacheService.findByUserId(internalUser.getId(), CreateMusicStatus.class);
        if (currentCache.isEmpty()) {
            saveCache(internalUser.getId(), ADDING_MUSIC, WRITE_TITLE_AWAITING, ADD_MUSIC);
            return CottageMusicResultDto.builder()
                    .status(WRITE_TITLE_AWAITING)
                    .build();
        }
        if (WRITE_TITLE_AWAITING.equals(currentCache.get().getData())) {
            userCacheService.removeByUserId(internalUser.getId());
            return musicService.create(musicQuery, internalUser.getId());
        }
        return CottageMusicResultDto.builder()
                .success(Boolean.FALSE)
                .status(CreateMusicStatus.NOT_STARTED)
                .build();
    }

    private void saveCache(Long userId, UserCacheStatus cacheStatus, CreateMusicStatus musicCreateStatus,
                           MessageType messageType) {
        UserCacheDto<CreateMusicStatus> cache = new UserCacheDto<>();
        cache.setUserId(userId);
        cache.setCacheStatus(cacheStatus);
        cache.setData(musicCreateStatus);
        cache.setCurrentMessageType(messageType);
        userCacheService.save(cache);
    }
}