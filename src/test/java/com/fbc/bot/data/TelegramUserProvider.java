package com.fbc.bot.data;

import org.telegram.telegrambots.meta.api.objects.User;

import static com.fbc.bot.data.UserDataProvider.*;

public class TelegramUserProvider {

    public static User getUnknownUser() {
        User user = new User();
        user.setUserName(UNKNOWN_NAME);
        user.setFirstName(UNKNOWN_NAME);
        user.setLastName(UNKNOWN_NAME);
        user.setId(UNKNOWN_ID);
        user.setIsBot(false);
        return user;
    }

    public static User getExistingUser() {
        User user = new User();
        user.setUserName(KNOWN_NAME);
        user.setFirstName(KNOWN_NAME);
        user.setLastName(KNOWN_NAME);
        user.setId(EXISTING_ID);
        user.setIsBot(false);
        return user;
    }
}