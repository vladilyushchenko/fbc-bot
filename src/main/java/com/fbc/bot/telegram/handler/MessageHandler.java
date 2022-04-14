package com.fbc.bot.telegram.handler;

import com.fbc.bot.telegram.model.MessageType;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Setter
@Getter
public abstract class MessageHandler {

    private MessageType messageType;

    public abstract BotApiMethod<?> handleMessage(Update update);
}