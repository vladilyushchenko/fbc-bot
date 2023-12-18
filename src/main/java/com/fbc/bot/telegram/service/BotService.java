package com.fbc.bot.telegram.service;

import com.fbc.bot.telegram.aspect.TelegramAudit;
import com.fbc.bot.telegram.message.MessageHandlerProvider;
import com.fbc.bot.telegram.message.input.MessageType;
import com.fbc.bot.telegram.message.MessageTypeResolver;
import com.fbc.bot.telegram.validator.BotRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class BotService {

    private final MessageHandlerProvider handlerProvider;
    private final MessageTypeResolver messageTypeResolver;
    private final BotRequestValidator botRequestValidator;

    @TelegramAudit
    public BotApiMethod<?> handleUpdate(Update update) {
        botRequestValidator.validateRequest(update);

        MessageType type = messageTypeResolver.getUserMessageType(update);

        return handlerProvider.getHandler(type).handleMessage(update);
    }
}