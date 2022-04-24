package com.fbc.bot.service.impl;

import com.fbc.bot.service.MessageTypeService;
import com.fbc.bot.service.message.MessageTypeMatcher;
import com.fbc.bot.telegram.model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

import static com.fbc.bot.telegram.model.MessageType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageTypeServiceImpl implements MessageTypeService {

    private final MessageTypeMatcher typeMatcher;
    private final Set<MessageType> inlineMessageTypes = Set.of(INLINE_SHARE_COCK_SIZE, INLINE_UNKNOWN);

    // in future take into account current user's bot state for interview-chatting
    @Override
    public MessageType getUserMessageType(Update update) {
        MessageType matchedType = typeMatcher.getMessageType(update);
        if (isInlineMessage(matchedType) && !update.hasInlineQuery()) {
            log.info("Trying to send InlineQuery command in simple chat");
            return UNKNOWN;
        }
        return matchedType;
    }

    private boolean isInlineMessage(MessageType type) {
        return inlineMessageTypes.contains(type);
    }
}