package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.telegram.message.input.MessageType;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandler {

    MessageType getMessageType();

    BotApiMethod<?> handleMessage(Update update);
}