package com.fbc.bot.service;

import com.fbc.bot.model.DailyCockSize;
import com.fbc.bot.model.User;
import com.fbc.bot.service.reply.CockSizeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

import static com.fbc.bot.model.UserStatus.CLUB_MEMBER;
import static java.lang.System.currentTimeMillis;
import static java.time.OffsetDateTime.now;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CockSizeService {

    private final UserService userService;
    private final CockSizeGenerator sizeGenerator;

    public String getCockSizeAnswer(User user) {
        updateUserCockSize(user);
        return sizeGenerator.generateAnswer(user);
    }

    // todo: try another cascade types to save and update DailyCockSize when saving/updating user
    public void updateUserCockSize(User user) {
        boolean noCockSize = isNull(user.getCockSize());
        if (noCockSize) {
            user.setCockSize(DailyCockSize.builder()
                    .size(generateCockSize(user))
                    .createdAt(now())
                    .updatedAt(now())
                    .userId(user.getId())
                    .user(user)
                    .build());
            userService.updateUser(user);
        }
        boolean cockSizeExpired = user.getCockSize().getUpdatedAt().isBefore(now().minus(Duration.ofDays(1)));
        if (cockSizeExpired) {
            user.getCockSize().setSize(generateCockSize(user));
            user.getCockSize().setUpdatedAt(now());
            userService.updateUser(user);
        }
    }

    private long generateCockSize(User user) {
        Random rnd = new Random(currentTimeMillis());
        if (CLUB_MEMBER.equals(user.getUserStatus())) {
            return (long) (25 / 3.0 * (rnd.nextGaussian()) + 35);
        }
        return (long) (5 * (rnd.nextGaussian()) + 15);
    }
}