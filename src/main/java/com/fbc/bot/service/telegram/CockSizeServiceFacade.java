package com.fbc.bot.service.telegram;

import com.fbc.bot.model.User;
import com.fbc.bot.service.CockSizeService;
import com.fbc.bot.service.UserService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CockSizeServiceFacade {

    private final CockSizeService cockSizeService;
    private final UserService userService;

    public String getCockSizeAnswer(org.telegram.telegrambots.meta.api.objects.User tgUser) {
        User user = Try.of(() -> userService.getUserByTelegramId(tgUser.getId()))
                .getOrElse(userService.createUser(tgUser));
        return cockSizeService.getCockSizeAnswer(user);
    }
}