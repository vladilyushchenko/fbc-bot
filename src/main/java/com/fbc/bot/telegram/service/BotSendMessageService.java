package com.fbc.bot.telegram.service;

import com.fbc.bot.config.properties.TelegramProperties;
import com.fbc.bot.telegram.client.TelegramApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotSendMessageService {

    private final TelegramApiClient telegramApiClient;
    private final TelegramProperties telegramProperties;

    public void sendMessage(String chatId, String message) {
        try {
            telegramApiClient.sendMessage(telegramProperties.getBot().getBotToken(), chatId, message);
        } catch (Exception e) {
            log.info("Message {} to chat {} wasn't sent because of exception: {}", message, chatId, e.getMessage());
        }
    }
}