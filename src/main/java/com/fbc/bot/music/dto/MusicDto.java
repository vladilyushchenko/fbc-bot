package com.fbc.bot.music.dto;

import com.fbc.bot.common.dto.BaseDto;
import lombok.Data;

@Data
public class MusicDto implements BaseDto {

    private String title;
    private String author;
    private String dataSource;
}