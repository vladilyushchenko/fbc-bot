package com.fbc.bot.service;

import com.fbc.bot.telegram.model.MessageType;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageTypeService {

    MessageType getUserMessageType(Update update);
}