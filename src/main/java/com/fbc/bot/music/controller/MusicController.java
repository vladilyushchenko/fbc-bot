package com.fbc.bot.music.controller;

import com.fbc.bot.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/music")
    public Object findByTitle(@RequestParam String title) {
        return musicService.create(title, 424189266L);
    }
}