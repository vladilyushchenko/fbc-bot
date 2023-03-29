package com.fbc.bot.telegram.service;

import com.fbc.bot.config.properties.TelegramProperties;
import com.fbc.bot.message.service.MessageTypeService;
import com.fbc.bot.telegram.handler.MessageHandlerProvider;
import com.fbc.bot.telegram.handler.input.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotService {

    private final MessageHandlerProvider handlerProvider;
    private final MessageTypeService messageTypeService;
    private final BotSendMessageService botSendMessageService;
    private final TelegramProperties telegramProperties;

    public BotApiMethod<?> handleUpdate(Update update) {
        log.info("handleUpdate() with update {}", update);
        if (!update.hasInlineQuery() && isNull(update.getMessage())) {
            log.info("INLINE QUERY WITH NULL MESSAGE");
            return null;
        }
        if (Objects.nonNull(update.getMessage())) {
            if (Objects.nonNull(update.getMessage().getViaBot())) {
                log.info("Ignore answer on via-bot messages");
                return null;
            }
        }

        sendAudit(update);

        MessageType type = messageTypeService.getUserMessageType(update);
        return handlerProvider.getHandler(type).handleMessage(update);
    }

    private void sendAudit(Update update) {
        try {
            String text = Objects.isNull(update.getMessage()) || Objects.isNull(update.getMessage().getText())
                    ? update.getInlineQuery().getQuery()
                    : update.getMessage().getText();
            final String author;
            if (update.hasInlineQuery()) {
                author = update.getInlineQuery().getFrom().getUserName();
            } else {
                author = Objects.isNull(update.getMessage().getFrom().getUserName())
                        ? update.getMessage().getFrom().getFirstName()
                        : "@".concat(update.getMessage().getFrom().getUserName());
            }
            botSendMessageService.sendMessage(telegramProperties.getBot().getAuditChatId(),
                    String.format("%s: %s", author, text));
        } catch (Exception e) {
            log.info("Exception while getting text of message for audit: {}", e);
        }
    }
}