package com.fbc.bot.telegram.message;

import com.fbc.bot.common.exception.ApiException;
import com.fbc.bot.common.constant.ExceptionType;
import com.fbc.bot.telegram.message.handlers.MessageHandler;
import com.fbc.bot.telegram.message.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MessageHandlerProvider {

    private final Map<MessageType, MessageHandler> messageHandlers;

    public MessageHandler getHandler(MessageType type) {
        if (!messageHandlers.containsKey(type)) {
            throw new ApiException(ExceptionType.TELEGRAM_MESSAGE_HANDLER_NOT_FOUND);
        }
        return messageHandlers.get(type);
    }
}