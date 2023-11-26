package com.fbc.bot.telegram.service;

import com.fbc.bot.common.config.properties.TelegramProperties;
import com.fbc.bot.telegram.client.TelegramApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotSendMessageService {

    private final TelegramApiClient telegramApiClient;
    private final TelegramProperties telegramProperties;

    public void sendMessageToDefaultChat(Update update) {
        if (isFromDefaultChat(update)) {
            return;
        }
        try {
            final String text = Optional.ofNullable(update.getMessage())
                    .map(Message::getText)
                    .orElseGet(() -> update.getInlineQuery().getQuery());
            final String author = "@".concat(update.hasInlineQuery()
                    ? update.getInlineQuery().getFrom().getUserName()
                    : getSimpleMessageAuthor(update)
            );
            sendMessage(telegramProperties.getBot().getAuditChatId(),
                    String.format("%s: %s", author, text));
        } catch (Exception e) {
            log.info("Exception while getting text of message for audit: {}", e);
        }
    }

    private boolean isFromDefaultChat(Update update) {
        return Optional.ofNullable(update)
                .map(Update::getMessage)
                .map(Message::getChatId)
                .map(chatId -> chatId.equals(telegramProperties.getBot().getAuditChatId()))
                .orElse(false);
    }

    private String getSimpleMessageAuthor(Update update) {
        return Optional.ofNullable(update.getMessage())
                .map(Message::getFrom)
                .map(User::getUserName)
                .orElseGet(() -> update.getMessage().getFrom().getFirstName());
    }

    public void sendMessage(Long chatId, String message) {
        try {
            telegramApiClient.sendMessage(telegramProperties.getBot().getBotToken(), chatId, message);
        } catch (Exception e) {
            log.info("Message {} to chat {} wasn't sent because of exception: {}", message, chatId, e);
        }
    }
}