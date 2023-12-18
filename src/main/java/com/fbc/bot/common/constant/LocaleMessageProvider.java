package com.fbc.bot.common.constant;

import com.fbc.bot.util.LocaleConstants;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocaleMessageProvider {
    private final MessageSource messageSource;

    public LocaleMessageProvider(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getLocalMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, LocaleConstants.LOCALE_RU);
    }
}