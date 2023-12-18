package com.fbc.bot.telegram.controller;

import com.fbc.bot.telegram.constant.BotRoutes;
import com.fbc.bot.telegram.service.BotService;
import jakarta.validation.constraints.NotNull;
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

    @PostMapping(BotRoutes.TELEGRAM_BOT)
    public BotApiMethod<?> onChatUpdate(@RequestBody @NotNull Update update) {
        return service.handleUpdate(update);
    }
}