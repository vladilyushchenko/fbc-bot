package com.fbc.bot.service.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleMessageProvider {
    private final Locale locale;
    private final MessageSource messageSource;

    public LocaleMessageProvider(@Value("ru-RU") String localeTag, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(localeTag);
    }

    public String getLocalMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, locale);
    }

    public String getLocalMessage(String messageCode, Object... args) {
        return messageSource.getMessage(messageCode, args, locale);
    }
}