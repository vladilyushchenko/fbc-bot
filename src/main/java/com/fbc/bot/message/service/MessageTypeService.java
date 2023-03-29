package com.fbc.bot.message.service;

import com.fbc.bot.telegram.handler.input.MessageType;
import com.fbc.bot.user.service.UserCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;
import java.util.Set;

import static com.fbc.bot.telegram.handler.input.MessageType.INLINE_GLOBAL;
import static com.fbc.bot.telegram.handler.input.MessageType.UNKNOWN;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageTypeService {

    private final MessageTypeMatcher typeMatcher;
    private final UserCacheService userCacheService;
    private static final Set<MessageType> inlineMessageTypes = Set.of(INLINE_GLOBAL);

    // in future take into account current user's bot state for interview-chatting
    public MessageType getUserMessageType(Update update) {
        MessageType matchedType = typeMatcher.getMessageType(update);
        if (Objects.nonNull(update.getMessage())) {
            var userCache = userCacheService.findByTelegramUserId(update.getMessage().getFrom().getId(), Object.class);
            if (userCache.isPresent() && Objects.nonNull(userCache.get().getCurrentMessageType())) {
                return userCache.get().getCurrentMessageType();
            }
        }
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