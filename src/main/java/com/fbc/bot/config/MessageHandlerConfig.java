package com.fbc.bot.config;

import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.handler.input.MessageType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Configuration
public class MessageHandlerConfig {

    @Bean
    public Map<MessageType, MessageHandler> messageHandlers(List<MessageHandler> handlers) {
        return handlers.stream().collect(toMap(MessageHandler::getMessageType, identity()));
    }
}