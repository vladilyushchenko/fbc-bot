package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.config.annotation.AdminCheck;
import com.fbc.bot.config.scheduled.AlcoholicBerealScheduler;
import com.fbc.bot.message.service.LocaleMessageProvider;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.handler.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.handler.input.MessageType.TURN_BEREAL_OFF;
import static com.fbc.bot.util.MessageKeyConstants.Response.ANSWER_BEREAL_TURNED_OFF;

@Component
@RequiredArgsConstructor
public class TurnBerealOffMessageHandler implements MessageHandler {

    private final AlcoholicBerealScheduler scheduler;
    private final LocaleMessageProvider messageService;

    @Override
    public MessageType getMessageType() {
        return TURN_BEREAL_OFF;
    }

    @Override
    @AdminCheck
    public BotApiMethod<?> handleMessage(Update update) {
        String answerMessage = messageService.getLocalMessage(ANSWER_BEREAL_TURNED_OFF);
        scheduler.setEnabled(false);
        return new SendMessage(String.valueOf(update.getMessage().getChatId()), answerMessage);
    }
}
