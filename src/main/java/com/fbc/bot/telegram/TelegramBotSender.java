package com.fbc.bot.telegram;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static java.util.Objects.nonNull;

@Slf4j
@Setter
@Getter
public class TelegramBotSender extends TelegramWebhookBot {

    private String webHookPath;
    private String botUsername;
    private String botToken;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        log.info("MESSAGE RECEIVED : " + update.getMessage());
        if (nonNull(update.getMessage()) && update.getMessage().hasText()) {
            long chadId = update.getMessage().getChatId();
            try {
                execute(new SendMessage(String.valueOf(chadId), "Hi, " + update.getMessage().getText()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public BotApiMethod<?> sendMessageToChat(BotApiMethod<?> apiMethod) {
        try {
            execute(apiMethod);
        } catch (TelegramApiException e) {
            log.warn(e.getMessage());
        }
        log.info("API METHOD {} sent", apiMethod);
        return apiMethod;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}