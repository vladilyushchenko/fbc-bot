package com.fbc.bot.service.impl;

import com.fbc.bot.service.BotService;
import com.fbc.bot.service.MessageTypeService;
import com.fbc.bot.telegram.handler.MessageHandlerFactory;
import com.fbc.bot.telegram.model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final MessageHandlerFactory handlerFactory;
    private final MessageTypeService messageTypeService;

    @Override
    public BotApiMethod<?> handleUpdate(Update update) {
        log.info("handleUpdate() with update {}", update);
        if (!update.hasInlineQuery() && isNull(update.getMessage())) {
            log.info("INLINE QUERY WITH NULL MESSAGE");
            return null;
        }
        MessageType type = messageTypeService.getUserMessageType(update);
        return handlerFactory.getHandler(type).handleMessage(update);
    }
}