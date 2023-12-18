package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.bereal.scheduler.AlcoholicBerealScheduler;
import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.telegram.message.input.MessageType;
import com.fbc.bot.user.model.User;
import com.fbc.bot.user.model.UserStatus;
import com.fbc.bot.user.service.UserService;
import com.fbc.bot.util.MessageKeyConstants.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.message.input.MessageType.TURN_BEREAL_ON;
import static com.fbc.bot.util.MessageKeyConstants.Response.ANSWER_BEREAL_TURNED_ON;

@Component
@RequiredArgsConstructor
public class TurnBerealOnMessageHandler implements MessageHandler {

    private final AlcoholicBerealScheduler scheduler;
    private final LocaleMessageProvider messageService;
    private final UserService userService;

    @Override
    public MessageType getMessageType() {
        return TURN_BEREAL_ON;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        User user = userService.getUserByTelegramId(update.getMessage().getFrom().getId());

        final String answerMessage;
        if (user.getUserStatus() != UserStatus.ADMIN) {
            answerMessage = messageService.getLocalMessage(Response.ANSWER_BEREAL_NOT_ADMIN);
        } else {
            scheduler.setEnabled(true);
            answerMessage = messageService.getLocalMessage(ANSWER_BEREAL_TURNED_ON);
        }

        return new SendMessage(String.valueOf(update.getMessage().getChatId()), answerMessage);
    }
}
