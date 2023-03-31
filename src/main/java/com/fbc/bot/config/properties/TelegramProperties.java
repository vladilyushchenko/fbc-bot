package com.fbc.bot.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("telegram")
public class TelegramProperties {

    private BotProperties bot;

    @Getter
    @Setter
    public static class BotProperties {
        private String botToken;
        private Long auditChatId;
        private String mainChatId;

        public BotProperties() {
        }
    }
}