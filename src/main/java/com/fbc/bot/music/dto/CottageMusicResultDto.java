package com.fbc.bot.music.dto;

import com.fbc.bot.common.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CottageMusicResultDto implements BaseDto {

    private Boolean isNew;
    private MusicDto music;
    private Boolean success;
    private CreateMusicStatus status;

    public enum CreateMusicStatus {
        NOT_STARTED,
        WRITE_TITLE_AWAITING,
        ADDED
    }
}