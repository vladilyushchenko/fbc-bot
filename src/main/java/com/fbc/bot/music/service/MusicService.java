package com.fbc.bot.music.service;

import com.fbc.bot.user.model.User;
import com.fbc.bot.user.repository.UserRepository;
import com.fbc.bot.user.service.UserService;
import com.fbc.bot.exception.EntityNotFoundException;
import com.fbc.bot.music.client.DeezerMusicClient;
import com.fbc.bot.music.dto.CottageMusicResultDto;
import com.fbc.bot.music.dto.MusicDto;
import com.fbc.bot.music.dto.deeezer.DeezerMusicResponseDto;
import com.fbc.bot.music.model.DataSource;
import com.fbc.bot.music.model.Music;
import com.fbc.bot.music.repository.MusicRepository;
import com.fbc.bot.music.service.mapper.MusicMapper;
import com.fbc.bot.util.HttpUtils;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import static com.fbc.bot.music.dto.CottageMusicResultDto.CreateMusicStatus.ADDED;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final DeezerMusicClient deezerMusicClient;
    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    public CottageMusicResultDto create(String musicQuery, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, userId));

        if (HttpUtils.isValidUrl(musicQuery)) {
            var dbEntity = musicRepository.findByLinkIgnoreCase(musicQuery);
            if (dbEntity.isPresent()) {
                user.getMusic().add(dbEntity.get());
                dbEntity.get().getUsers().add(user);
                musicRepository.save(dbEntity.get());
                return CottageMusicResultDto.builder()
                        .isNew(false)
                        .success(true)
                        .music(musicMapper.toDto(dbEntity.get()))
                        .status(ADDED)
                        .build();
            }
            var entity = new Music();
            entity.setTitle(musicQuery);
            entity.setAuthor(musicQuery);
            entity.setLink(musicQuery);
            entity.setDataSource(DataSource.LINK);
            entity.setUsers(Sets.newHashSet(user));
            entity = musicRepository.save(entity);
            return CottageMusicResultDto.builder()
                    .isNew(true)
                    .success(true)
                    .status(ADDED)
                    .music(musicMapper.toDto(entity))
                    .build();
        }

        var musicResponse = deezerMusicClient.getTrack(musicQuery);
        if (CollectionUtils.isEmpty(musicResponse.getData())) {
            return CottageMusicResultDto.builder()
                    .success(false)
                    .build();
        }
        return createMusicFromExternal(user, musicResponse);
    }

    private CottageMusicResultDto createMusicFromExternal(User user, DeezerMusicResponseDto musicResponse) {
        boolean isNew = false;
        var musicDto = musicResponse.getData().get(0);
        var music = musicRepository.findByLinkIgnoreCase(musicDto.getLink());

        MusicDto resultValue;
        if (music.isEmpty()) {
            var entity = musicMapper.toEntityIgnoreId(musicDto);
            entity.setDataSource(DataSource.DEEZER);
            entity.setUsers(Sets.newHashSet(user));
            musicRepository.save(entity);
            resultValue = musicMapper.toDto(entity);
            isNew = true;
        } else {
            music.get().getUsers().add(user);
//            user.getMusic().add(music.get());
//            userService.updateUser(user);
            musicRepository.save(music.get());
            resultValue = musicMapper.toDto(music.get());
        }

        return CottageMusicResultDto.builder()
                .isNew(isNew)
                .success(true)
                .status(ADDED)
                .music(resultValue)
                .build();
    }
}