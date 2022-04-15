package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.service.message.LocaleMessageProvider;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.model.MessageType.UNKNOWN;
import static com.fbc.bot.util.MessageKeyConstants.ANSWER_UNKNOWN;

@Component
@RequiredArgsConstructor
public class UnknownMessageHandler implements MessageHandler {

    private final LocaleMessageProvider messageService;

    @Override
    public MessageType getMessageType() {
        return UNKNOWN;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        String answerMessage = messageService.getLocalMessage(ANSWER_UNKNOWN);
        if (update.hasInlineQuery()) {
            return null;
        }
        return new SendMessage(update.getMessage().getChatId(), answerMessage);
    }
}