package com.fbc.bot.telegram.handler;

import com.fbc.bot.exception.SystemException;
import com.fbc.bot.telegram.model.MessageType;
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
            throw new SystemException(format("No handlers for MessageType %s", type));
        }
        return messageHandlers.get(type);
    }
}