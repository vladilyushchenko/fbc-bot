package com.fbc.bot.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface MainMenuService {

    BotApiMethod<?> getStartMenu(long chatId, String textMessage);
}