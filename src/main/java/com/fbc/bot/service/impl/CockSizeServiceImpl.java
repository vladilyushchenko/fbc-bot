package com.fbc.bot.service.impl;

import com.fbc.bot.model.DailyCockSize;
import com.fbc.bot.model.User;
import com.fbc.bot.repository.UserRepository;
import com.fbc.bot.service.CockSizeService;
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
public class CockSizeServiceImpl implements CockSizeService {

    private final UserRepository userRepository;

    @Override
    public void updateUserCockSize(User user) {
        boolean noCockSize = isNull(user.getCockSize());
        if (noCockSize) {
            user.setCockSize(DailyCockSize.builder()
                    .size(generateCockSize(user))
                    .updatedAt(now())
                    .createdAt(now())
                    .userId(user.getId())
                    .user(user)
                    .build());
            userRepository.save(user);
        }
        boolean cockSizeExpired = user.getCockSize().getUpdatedAt().isBefore(now().minus(Duration.ofDays(1)));
        if (cockSizeExpired) {
            user.getCockSize().setSize(generateCockSize(user));
            user.getCockSize().setUpdatedAt(now());
            userRepository.save(user);
        }
    }

    private long generateCockSize(User user) {
        Random rnd = new Random(currentTimeMillis() % user.getTelegramId());
        if (CLUB_MEMBER.equals(user.getUserStatus())) {
            return (long) (25 / 3 * (rnd.nextGaussian()) + 35);
        }
        return (long) (5 * (rnd.nextGaussian()) + 15);
    }
}