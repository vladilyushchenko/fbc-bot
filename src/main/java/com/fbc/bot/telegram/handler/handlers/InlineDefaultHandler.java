package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.handler.input.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fbc.bot.telegram.handler.input.MessageType.INLINE_UNKNOWN;

@Component
@RequiredArgsConstructor
public class InlineDefaultHandler implements MessageHandler {

    private final InlineCockSizeHandler cockSizeHandler;

    @Override
    public MessageType getMessageType() {
        return INLINE_UNKNOWN;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        return cockSizeHandler.handleMessage(update);
    }
}