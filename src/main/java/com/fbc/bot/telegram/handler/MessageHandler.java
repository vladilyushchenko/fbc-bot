package com.fbc.bot.telegram.handler;

import com.fbc.bot.telegram.model.MessageType;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandler {

    MessageType getMessageType();

    BotApiMethod<?> handleMessage(Update update);
}