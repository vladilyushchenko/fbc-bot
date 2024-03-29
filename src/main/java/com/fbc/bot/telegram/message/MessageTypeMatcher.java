package com.fbc.bot.telegram.message;

import com.fbc.bot.telegram.message.input.IncomeCommand;
import com.fbc.bot.telegram.message.input.MessageType;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

import static com.fbc.bot.telegram.message.input.MessageType.INLINE_GLOBAL;
import static com.fbc.bot.telegram.message.input.MessageType.UNKNOWN;
import static com.fbc.bot.util.LocaleConstants.LOCALE_RU;
import static java.util.Arrays.stream;

/*
    In future this component will match incoming input messages
     from any language(not only russian) to application language
 */
@Getter
@Component
public class MessageTypeMatcher {

    private final Map<String, MessageType> inputToTypeMap = new HashMap<>();

    public MessageTypeMatcher(MessageSource messageSource) {
        initMessageTypeMap(messageSource);
    }

    private void initMessageTypeMap(MessageSource messageSource) {
        stream(IncomeCommand.values())
                .forEach(key -> {
                    String resourceMsg = messageSource.getMessage(key.getKey(), null, LOCALE_RU);
                    inputToTypeMap.put(resourceMsg, key.getType());
                });
    }

    public MessageType getMessageType(Update update) {
        if (update.hasInlineQuery()) {
            return inputToTypeMap.getOrDefault(update.getInlineQuery().getQuery(), INLINE_GLOBAL);
        }
        return inputToTypeMap.getOrDefault(update.getMessage().getText(), UNKNOWN);
    }
}