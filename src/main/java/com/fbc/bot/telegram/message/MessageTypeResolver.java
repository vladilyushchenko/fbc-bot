package com.fbc.bot.telegram.message;

import com.fbc.bot.telegram.message.input.MessageType;
import com.fbc.bot.user.service.UserCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

import static com.fbc.bot.telegram.message.input.MessageType.UNKNOWN;

@Service
@RequiredArgsConstructor
public class MessageTypeResolver {

    private final MessageTypeMatcher typeMatcher;
    private final UserCacheService userCacheService;

    public MessageType getUserMessageType(Update update) {
        MessageType matchedType = typeMatcher.getMessageType(update);
        if (Objects.nonNull(update.getMessage())) {
            var userCache = userCacheService.findByTelegramUserId(update.getMessage().getFrom().getId(), Object.class);
            if (userCache.isPresent() && Objects.nonNull(userCache.get().getCurrentMessageType())) {
                return userCache.get().getCurrentMessageType();
            }
        }
        if (isInlineMessage(matchedType) && !update.hasInlineQuery()) {
            return UNKNOWN;
        }
        return matchedType;
    }

    private boolean isInlineMessage(MessageType type) {
        return MessageType.getInlineMessageTypes().contains(type);
    }
}