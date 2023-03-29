package com.fbc.bot.telegram.handler;

import com.fbc.bot.exception.ApiException;
import com.fbc.bot.telegram.handler.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class MessageHandlerProvider {

    private final Map<MessageType, MessageHandler> messageHandlers;

    public MessageHandler getHandler(MessageType type) {
        if (!messageHandlers.containsKey(type)) {
            throw new ApiException(format("No handlers for MessageType %s", type));
        }
        return messageHandlers.get(type);
    }
}