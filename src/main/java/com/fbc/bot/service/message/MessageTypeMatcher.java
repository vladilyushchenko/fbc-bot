package com.fbc.bot.service.message;

import com.fbc.bot.telegram.model.MessageType;
import com.fbc.bot.telegram.model.ResourceMessageKey;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

import static com.fbc.bot.telegram.model.MessageType.UNKNOWN;
import static com.fbc.bot.util.LocaleConstants.LOCALE_RU;
import static java.util.Arrays.stream;

/*
    In future this component will match input incoming messages
     from any language(not only russian) to application language
 */
@Getter
@Component
public class MessageTypeMatcher {

    private final Map<String, MessageType> inputToTypeMap = new HashMap<>();

    public MessageTypeMatcher(MessageSource messageSource) {
        initMessageTypeMatcher(messageSource);
    }

    private void initMessageTypeMatcher(MessageSource messageSource) {
        stream(ResourceMessageKey.values())
                .forEach(key -> {
                    String resourceMsg = messageSource.getMessage(key.getValue(), null, LOCALE_RU);
                    inputToTypeMap.put(resourceMsg, key.getType());
                });
    }

    public MessageType getMessageType(Update update) {
        return inputToTypeMap.getOrDefault(update.getMessage().getText(), UNKNOWN);
    }
}