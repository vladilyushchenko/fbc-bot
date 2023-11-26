package com.fbc.bot.user.service;

import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_REPLY_FIND_FRIEND;
import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_REPLY_FIND_FRIEND_WHEN_ALONE;

@Component
@RequiredArgsConstructor
public class FindFriendTextGenerator {

    private final UserService userService;
    private final Random random = new Random();
    private final LocaleMessageProvider localeMessageProvider;

    public String getText(Long telegramId) {
        User firstUser = userService.getUserByTelegramId(telegramId);
        List<User> users = userService.getAll();

        if (users.size() < 2) {
            return String.format(localeMessageProvider.getLocalMessage(INLINE_QUERY_REPLY_FIND_FRIEND_WHEN_ALONE),
                    "@" + firstUser.getUserName());
        }

        int index = random.nextInt(users.size());
        User secondUser = users.get(index);
        while (firstUser.getUserName().equals(secondUser.getUserName())) {
            index = random.nextInt(users.size());
            secondUser = users.get(index);
        }

        return String.format(localeMessageProvider.getLocalMessage(INLINE_QUERY_REPLY_FIND_FRIEND),
                "@" + secondUser.getUserName());
    }
}
