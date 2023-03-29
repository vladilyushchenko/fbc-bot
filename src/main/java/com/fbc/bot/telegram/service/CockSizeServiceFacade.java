package com.fbc.bot.telegram.service;

import com.fbc.bot.user.model.User;
import com.fbc.bot.user.service.UserService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CockSizeServiceFacade {

    private final CockSizeService cockSizeService;
    private final UserService userService;

    @Transactional
    public String getCockSizeAnswer(org.telegram.telegrambots.meta.api.objects.User tgUser) {
        User user = Try.of(() -> userService.getUserByTelegramId(tgUser.getId()))
                .getOrElse(() -> userService.createUser(tgUser));
        return cockSizeService.getCockSizeAnswer(user);
    }
}