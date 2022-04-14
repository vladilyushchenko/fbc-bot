package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.service.message.LocaleMessageProvider;
import com.fbc.bot.telegram.handler.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.model.MessageType.UNKNOWN;
import static com.fbc.bot.util.MessageKeyConstants.ANSWER_UNKNOWN;

@Component
public class UnknownMessageHandler extends MessageHandler {

    private final LocaleMessageProvider messageService;

    public UnknownMessageHandler(LocaleMessageProvider messageService) {
        this.messageService = messageService;
        super.setMessageType(UNKNOWN);
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        String answerMessage = messageService.getLocalMessage(ANSWER_UNKNOWN);
        return new SendMessage(update.getMessage().getChatId(), answerMessage);
    }
}