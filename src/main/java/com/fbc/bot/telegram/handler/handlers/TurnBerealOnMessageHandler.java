package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.config.annotation.AdminCheckAudit;
import com.fbc.bot.config.scheduled.AlcoholicBerealScheduler;
import com.fbc.bot.message.service.LocaleMessageProvider;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.handler.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.handler.input.MessageType.TURN_BEREAL_ON;
import static com.fbc.bot.util.MessageKeyConstants.Response.ANSWER_BEREAL_TURNED_ON;

@Component
@RequiredArgsConstructor
public class TurnBerealOnMessageHandler implements MessageHandler {

    private final AlcoholicBerealScheduler scheduler;
    private final LocaleMessageProvider messageService;

    @Override
    public MessageType getMessageType() {
        return TURN_BEREAL_ON;
    }

    @Override
    @AdminCheckAudit
    public BotApiMethod<?> handleMessage(Update update) {
        String answerMessage = messageService.getLocalMessage(ANSWER_BEREAL_TURNED_ON);
        scheduler.setEnabled(true);
        return new SendMessage(String.valueOf(update.getMessage().getChatId()), answerMessage);
    }
}
