package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.service.message.LocaleMessageProvider;
import com.fbc.bot.telegram.handler.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.model.MessageType.JOIN_PARTY;
import static com.fbc.bot.util.MessageKeyConstants.ANSWER_JOIN_PARTY;

@Component
public class JoinPartyMessageHandler extends MessageHandler {

    private final LocaleMessageProvider messageService;

    public JoinPartyMessageHandler(LocaleMessageProvider messageService) {
        this.messageService = messageService;
        super.setMessageType(JOIN_PARTY);
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        String answerMessage = messageService.getLocalMessage(ANSWER_JOIN_PARTY);
        return new SendMessage(update.getMessage().getChatId(), answerMessage);
    }
}