package com.fbc.bot.telegram.validator;

import com.fbc.bot.common.constant.ExceptionType;
import com.fbc.bot.common.exception.ApiException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class BotRequestValidator {

    public void validateRequest(Update update) {
        final boolean isEmptyInlineQuery = !update.hasInlineQuery() && isNull(update.getMessage());

        if (isEmptyInlineQuery) {
            throw new ApiException(ExceptionType.EMPTY_INLINE_QUERY_NOT_ALLOWED);
        }

        final boolean isAnswerOnBotMessage = Optional.of(update)
                .map(Update::getMessage)
                .map(Message::getViaBot)
                .isPresent();

        if (isAnswerOnBotMessage) {
            throw new ApiException(ExceptionType.ANSWERING_ON_BOT_MESSAGE_ARE_NOT_HANDLED);
        }
    }
}