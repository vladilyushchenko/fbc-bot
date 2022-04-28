package com.fbc.bot.telegram.handler;

import com.fbc.bot.exception.SystemException;
import com.fbc.bot.telegram.model.MessageType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Component
public class MessageHandlerFactory {

    private final Map<MessageType, MessageHandler> handlers;

    public MessageHandlerFactory(List<MessageHandler> handlers) {
        this.handlers = new EnumMap<>(MessageType.class);
        handlers.forEach(handler -> this.handlers.put(handler.getMessageType(), handler));
    }

    public MessageHandler getHandler(MessageType type) {
        if (!handlers.containsKey(type)) {
            throw new SystemException(format("No handlers for MessageType %s", type));
        }
        return handlers.get(type);
    }
}