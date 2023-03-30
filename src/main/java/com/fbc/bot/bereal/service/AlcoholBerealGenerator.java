package com.fbc.bot.bereal.service;

import com.fbc.bot.user.model.User;
import com.fbc.bot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlcoholBerealGenerator {

    private final UserService userService;
    private final Random random = new Random();

    public String generateMessage() {
        List<User> users = userService.getAll();
        int firstUserIndex = random.nextInt(users.size());
        int secondUserIndex = random.nextInt(users.size());

        while (firstUserIndex == secondUserIndex) {
            secondUserIndex = random.nextInt(users.size());
        }

        return generateText(users.get(firstUserIndex), users.get(secondUserIndex));
    }

    private String generateText(User firstUser, User secondUser) {
        return "@" + firstUser.getUserName() + " бухает с " + "@" + secondUser.getUserName();
    }
}
