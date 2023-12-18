package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.telegram.message.input.MessageType;
import com.fbc.bot.telegram.menu.MainMenuProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.message.input.MessageType.START;
import static com.fbc.bot.util.MessageKeyConstants.Response.ANSWER_START;

@Component
@RequiredArgsConstructor
public class StartMessageHandler implements MessageHandler {

    private final LocaleMessageProvider messageService;
    private final MainMenuProvider menuService;

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