package com.fbc.bot.music.mapper;

import com.fbc.bot.music.dto.MusicDto;
import com.fbc.bot.music.dto.deeezer.DeezerMusicResponseDto;
import com.fbc.bot.music.model.Music;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface MusicMapper {

    MusicDto toDto(Music music);

    @Mapping(target = "author", source = "dto.artist.name")
    @Mapping(target = "id", ignore = true)
    Music toEntityIgnoreId(DeezerMusicResponseDto.MusicDto dto);
}