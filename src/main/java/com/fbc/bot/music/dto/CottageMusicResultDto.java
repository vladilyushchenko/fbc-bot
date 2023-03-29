package com.fbc.bot.music.dto;

import com.fbc.bot.common.dto.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CottageMusicResultDto implements BaseDto {

    public Boolean isNew;
    public MusicDto music;
    public Boolean success;
    public CreateMusicStatus status;

    public enum CreateMusicStatus {
        NOT_STARTED,
        WRITE_TITLE_AWAITING,
        ADDED
    }
}