package com.fbc.bot.user.service;

import com.fbc.bot.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class FindFriendTextGenerator {

    private final UserService userService;
    public static final String template = "%s приглашает %s на бар";
    private final Random random = new Random();

    public String getText(Long telegramId) {
        User firstUser = userService.getUserByTelegramId(telegramId);
        List<User> users = userService.getAll();

        int index = random.nextInt(users.size());
        User secondUser = users.get(index);
        while (firstUser.getUserName().equals(secondUser.getUserName())) {
            index = random.nextInt(users.size());
            secondUser = users.get(index);
        }

        return String.format(template, "@" + firstUser.getUserName(), "@" + secondUser.getUserName());
    }
}
