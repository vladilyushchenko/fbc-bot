package com.fbc.bot.music.client;

import com.fbc.bot.music.dto.deeezer.DeezerMusicResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "deezer-client", url = "https://api.deezer.com")
public interface DeezerMusicClient {

    @GetMapping("/search?q={title}")
    DeezerMusicResponseDto getTrack(@PathVariable String title);
}