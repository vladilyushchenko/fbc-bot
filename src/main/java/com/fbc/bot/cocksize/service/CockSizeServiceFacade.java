package com.fbc.bot.cocksize.service;

import com.fbc.bot.user.service.UserService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
@RequiredArgsConstructor
public class CockSizeServiceFacade {

    private final CockSizeService cockSizeService;
    private final UserService userService;

    @Transactional
    public String getCockSizeAnswer(User tgUser) {
        var internalUser = Try.of(() -> userService.getUserByTelegramId(tgUser.getId()))
                .getOrElse(() -> userService.createUser(tgUser));
        return cockSizeService.getCockSizeAnswer(internalUser);
    }
}