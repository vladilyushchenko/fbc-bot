package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.service.message.LocaleMessageProvider;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.service.MainMenuService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.model.MessageType.START;
import static com.fbc.bot.util.MessageKeyConstants.ANSWER_START;

@Component
public class StartMessageHandler extends MessageHandler {

    private final LocaleMessageProvider messageService;
    private final MainMenuService menuService;

    public StartMessageHandler(LocaleMessageProvider messageService, MainMenuService menuService) {
        this.messageService = messageService;
        this.menuService = menuService;
        super.setMessageType(START);
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        String text = messageService.getLocalMessage(ANSWER_START);
        return menuService.getStartMenu(update.getMessage().getChatId(), text);
    }
}