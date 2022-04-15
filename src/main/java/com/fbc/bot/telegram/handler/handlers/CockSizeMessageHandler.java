package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.model.User;
import com.fbc.bot.service.CockSizeService;
import com.fbc.bot.service.UserService;
import com.fbc.bot.service.reply.CockSizeGenerator;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.fbc.bot.telegram.model.MessageType.SHARE_COCK_SIZE;

@Service
@RequiredArgsConstructor
public class CockSizeMessageHandler implements MessageHandler {

    private final UserService userService;
    private final CockSizeGenerator sizeGenerator;
    private final CockSizeService sizeService;

    @Override
    public MessageType getMessageType() {
        return SHARE_COCK_SIZE;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        var tgUser = update.getMessage().getFrom();
        Optional<User> user = userService.getUserByTelegramId(tgUser.getId());
        if (user.isEmpty()) {
            user = Optional.of(userService.createUser(tgUser));
        }
        sizeService.updateUserCockSize(user.get());
        String answer = sizeGenerator.generateAnswer(user.get());
        return new SendMessage(update.getMessage().getChatId(), answer);
    }
}