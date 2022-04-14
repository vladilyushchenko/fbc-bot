package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.model.User;
import com.fbc.bot.service.CockSizeService;
import com.fbc.bot.service.UserService;
import com.fbc.bot.service.reply.CockSizeGenerator;
import com.fbc.bot.telegram.handler.MessageHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.fbc.bot.telegram.model.MessageType.SHARE_COCK_SIZE;

@Service
public class CockSizeMessageHandler extends MessageHandler {

    private final UserService userService;
    private final CockSizeGenerator sizeGenerator;
    private final CockSizeService sizeService;

    public CockSizeMessageHandler(UserService userService,
                                  CockSizeGenerator sizeGenerator,
                                  CockSizeService sizeService) {
        this.userService = userService;
        this.sizeGenerator = sizeGenerator;
        this.sizeService = sizeService;
        super.setMessageType(SHARE_COCK_SIZE);
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        var tgUser = update.getMessage().getFrom();
        Optional<User> user = userService.getUserByTelegramId(tgUser.getId());
        String answer;
        if (user.isEmpty()) {
            user = Optional.of(userService.createUser(tgUser));
        }
        sizeService.updateUserCockSize(user.get());
        answer = sizeGenerator.generateAnswer(user.get());
        return new SendMessage(update.getMessage().getChatId(), answer);
    }
}