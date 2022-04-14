package com.fbc.bot.config;

import com.fbc.bot.telegram.TelegramBotSender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.DefaultBotOptions.ProxyType;
import org.telegram.telegrambots.meta.ApiContext;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {

    private String webHookPath;
    private String botUsername;
    private String botToken;
    private ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public TelegramBotSender telegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
//        options.setProxyHost(proxyHost);
//        options.setProxyPort(proxyPort);
//        options.setProxyType(proxyType);

        TelegramBotSender handler = new TelegramBotSender();
        handler.setBotToken(botToken);
        handler.setBotUsername(botUsername);
        handler.setWebHookPath(webHookPath);
        return handler;
    }
}