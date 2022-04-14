package com.fbc.bot.controller;

import com.fbc.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequiredArgsConstructor
public class BotController {

    private final BotService service;

    @RequestMapping(value = "/", method = POST)
    public Object onChatUpdate(@RequestBody Update update) {
        return service.handleUpdate(update);
    }
}
