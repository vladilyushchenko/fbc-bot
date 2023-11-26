package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.telegram.message.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.message.input.MessageType.JOIN_PARTY;
import static com.fbc.bot.util.MessageKeyConstants.Response.ANSWER_JOIN_PARTY;

@Component
@RequiredArgsConstructor
public class JoinPartyMessageHandler implements MessageHandler {

    private final LocaleMessageProvider messageService;

    @Override
    public MessageType getMessageType() {
        return JOIN_PARTY;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        String answerMessage = messageService.getLocalMessage(ANSWER_JOIN_PARTY);
        return new SendMessage(String.valueOf(update.getMessage().getChatId()), answerMessage);
    }
}