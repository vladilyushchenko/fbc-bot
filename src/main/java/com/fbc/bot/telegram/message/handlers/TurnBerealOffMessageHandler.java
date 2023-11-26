package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.bereal.scheduler.AlcoholicBerealScheduler;
import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.telegram.message.input.MessageType;
import com.fbc.bot.user.model.User;
import com.fbc.bot.user.model.UserStatus;
import com.fbc.bot.user.service.UserService;
import com.fbc.bot.util.MessageKeyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.message.input.MessageType.TURN_BEREAL_OFF;
import static com.fbc.bot.util.MessageKeyConstants.Response.ANSWER_BEREAL_TURNED_OFF;

@Component
@RequiredArgsConstructor
public class TurnBerealOffMessageHandler implements MessageHandler {

    private final AlcoholicBerealScheduler scheduler;
    private final LocaleMessageProvider messageService;
    private final UserService userService;

    @Override
    public MessageType getMessageType() {
        return TURN_BEREAL_OFF;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        User user = userService.getUserByTelegramId(update.getMessage().getFrom().getId());

        final String answerMessage;
        if (user.getUserStatus() != UserStatus.ADMIN) {
            answerMessage = messageService.getLocalMessage(MessageKeyConstants.Response.ANSWER_BEREAL_NOT_ADMIN);
        } else {
            scheduler.setEnabled(false);
            answerMessage = messageService.getLocalMessage(ANSWER_BEREAL_TURNED_OFF);
        }

        return new SendMessage(String.valueOf(update.getMessage().getChatId()), answerMessage);
    }
}
