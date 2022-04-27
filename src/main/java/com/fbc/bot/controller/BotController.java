package com.fbc.bot.controller;

import com.fbc.bot.service.telegram.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class BotController {

    private final BotService service;

    @PostMapping("/")
    public BotApiMethod<?> onChatUpdate(@RequestBody Update update) {
        return service.handleUpdate(update);
    }
}