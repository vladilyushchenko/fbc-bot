package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.service.message.LocaleMessageProvider;
import com.fbc.bot.service.telegram.MainMenuService;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.model.MessageType.START;
import static com.fbc.bot.util.MessageKeyConstants.ANSWER_START;

@Component
@RequiredArgsConstructor
public class StartMessageHandler implements MessageHandler {

    private final LocaleMessageProvider messageService;
    private final MainMenuService menuService;

    @Override
    public MessageType getMessageType() {
        return START;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        String text = messageService.getLocalMessage(ANSWER_START);
        return menuService.getStartMenu(update.getMessage().getChatId(), text);
    }
}