package com.fbc.bot.music.dto.deeezer;

import com.fbc.bot.common.dto.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class DeezerMusicResponseDto implements BaseDto {

    private List<MusicDto> data;

    @Data
    public static class MusicDto {

        private ArtistDto artist;
        private String title;
        private String link;
        private Long id;

        @Data
        public static class ArtistDto {
            private String name;
            private Long id;
            private String link;
        }
    }
}