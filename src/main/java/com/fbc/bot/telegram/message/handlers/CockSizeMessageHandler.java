package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.cocksize.service.CockSizeServiceFacade;
import com.fbc.bot.telegram.message.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.message.input.MessageType.SHARE_COCK_SIZE;

@Service
@RequiredArgsConstructor
public class CockSizeMessageHandler implements MessageHandler {

    private final CockSizeServiceFacade sizeServiceFacade;

    @Override
    public MessageType getMessageType() {
        return SHARE_COCK_SIZE;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        var tgUser = update.getMessage().getFrom();
        String answer = sizeServiceFacade.getCockSizeAnswer(tgUser);
        return new SendMessage(String.valueOf(update.getMessage().getChatId()), answer);
    }
}