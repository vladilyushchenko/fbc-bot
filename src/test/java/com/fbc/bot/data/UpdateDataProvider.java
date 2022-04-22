package com.fbc.bot.data;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

import static com.fbc.bot.data.UserDataProvider.UNKNOWN_ID;
import static com.fbc.bot.data.UserDataProvider.UNKNOWN_NAME;

public class UpdateDataProvider {

    public static final String INLINE_SHARE_COCK_SIZE = "Share cocksize";
    public static final int UNKNOWN_MESSAGE_ID = 1;
    public static final String OFFSET = "1";
    public static final int COCK_SIZE_INDEX = 0;

    public static Update getUpdateWithNonExistingUser() {
        Update update = new Update();
        Message message = new Message();
        message.setMessageId(UNKNOWN_MESSAGE_ID);
        update.setInlineQuery(InlineQuery.builder()
                .query(INLINE_SHARE_COCK_SIZE)
                .from(new User(UNKNOWN_ID, UNKNOWN_NAME, false))
                .id(String.valueOf(UNKNOWN_ID))
                .offset(OFFSET)
                .build());
        return update;
    }
}