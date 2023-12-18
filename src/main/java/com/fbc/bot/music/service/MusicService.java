package com.fbc.bot.music.service;

import com.fbc.bot.common.exception.EntityNotFoundException;
import com.fbc.bot.music.client.DeezerMusicClient;
import com.fbc.bot.music.dto.CottageMusicResultDto;
import com.fbc.bot.music.dto.MusicDto;
import com.fbc.bot.music.dto.deeezer.DeezerMusicResponseDto;
import com.fbc.bot.music.mapper.MusicMapper;
import com.fbc.bot.music.model.DataSource;
import com.fbc.bot.music.model.Music;
import com.fbc.bot.music.repository.MusicRepository;
import com.fbc.bot.user.model.User;
import com.fbc.bot.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public CottageMusicResultDto create(String musicQuery, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, userId));

        if (HttpUtils.isValidUrl(musicQuery)) {
            var existingMusic = musicRepository.findByLinkIgnoreCase(musicQuery);
            if (existingMusic.isPresent()) {
                user.getMusic().add(existingMusic.get());
                existingMusic.get().getUsers().add(user);
                musicRepository.save(existingMusic.get());
                return CottageMusicResultDto.builder()
                        .isNew(false)
                        .success(true)
                        .music(musicMapper.toDto(existingMusic.get()))
                        .status(ADDED)
                        .build();
            }
            var entity = new Music();
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
        var existingMusic = musicRepository.findByLinkIgnoreCase(musicDto.getLink());

        final MusicDto resultValue;
        if (existingMusic.isEmpty()) {
            var entity = musicMapper.toEntityIgnoreId(musicDto);
            entity.setDataSource(DataSource.DEEZER);
            entity.setUsers(Sets.newHashSet(user));
            musicRepository.save(entity);
            resultValue = musicMapper.toDto(entity);
            isNew = true;
        } else {
            existingMusic.get().getUsers().add(user);
            musicRepository.save(existingMusic.get());
            resultValue = musicMapper.toDto(existingMusic.get());
        }

        return CottageMusicResultDto.builder()
                .isNew(isNew)
                .success(true)
                .status(ADDED)
                .music(resultValue)
                .build();
    }
}