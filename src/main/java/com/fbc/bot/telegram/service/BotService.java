package com.fbc.bot.telegram.service;

import com.fbc.bot.config.annotation.TelegramAudit;
import com.fbc.bot.message.service.MessageTypeService;
import com.fbc.bot.telegram.handler.MessageHandlerProvider;
import com.fbc.bot.telegram.handler.input.MessageType;
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
public class BotService {

    private final MessageHandlerProvider handlerProvider;
    private final MessageTypeService messageTypeService;

    @TelegramAudit
    public BotApiMethod<?> handleUpdate(Update update) {
        log.info("handleUpdate() with update {}", update);
        if (!update.hasInlineQuery() && isNull(update.getMessage())) {
            log.info("INLINE QUERY WITH NULL MESSAGE");
            return null;
        }
        if (Objects.nonNull(update.getMessage())) {
            if (Objects.nonNull(update.getMessage().getViaBot())) {
                log.info("Ignore answer on via-bot messages");
                return null;
            }
        }

        MessageType type = messageTypeService.getUserMessageType(update);
        return handlerProvider.getHandler(type).handleMessage(update);
    }
}