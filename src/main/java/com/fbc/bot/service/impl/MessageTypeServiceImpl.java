package com.fbc.bot.service.impl;

import com.fbc.bot.service.message.MessageTypeMatcher;
import com.fbc.bot.service.MessageTypeService;
import com.fbc.bot.telegram.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class MessageTypeServiceImpl implements MessageTypeService {

    private final MessageTypeMatcher typeMatcher;

    // take into account current user's bot state!!!
    @Override
    public MessageType getUserMessageType(Update update) {
        return typeMatcher.getMessageType(update);
    }
}