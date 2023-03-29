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

//    public BotProperties getBot() {
//        return bot;
//    }
//
//    public void setBot(BotProperties bot) {
//        this.bot = bot;
//    }

    @Getter
    @Setter
    public static class BotProperties {
        private String botToken;
        private String auditChatId;

        public BotProperties() {
        }

//        public String getBotToken() {
//            return botToken;
//        }
//
//        public void setBotToken(String botToken) {
//            this.botToken = botToken;
//        }
    }
}