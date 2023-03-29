package com.fbc.bot.data;

import com.fbc.bot.user.dto.UserDto;
import com.fbc.bot.cocksize.model.DailyCockSize;
import com.fbc.bot.user.model.User;

import java.time.Duration;
import java.time.OffsetDateTime;

import static com.fbc.bot.user.model.UserStatus.UNKNOWN;
import static java.time.temporal.ChronoUnit.DAYS;

public class UserDataProvider {

    public static final String PREPARED_ANSWER = "answer";
    public static final String UNKNOWN_NAME = "UNKNOWN";
    public static final String KNOWN_NAME = "KNOWN";
    public static final long UNKNOWN_ID = 1L;
    public static final long EXISTING_ID = 2L;
    public static final OffsetDateTime UNKNOWN_CREATE_DATE = OffsetDateTime.now().minus(Duration.of(3L, DAYS));
    public static final OffsetDateTime UNKNOWN_UPDATE_DATE = OffsetDateTime.now().minus(Duration.of(2L, DAYS));
    public static final long OLD_SIZE = 1L;

    public static User getExistingCockSizeUnknownUser() {
        User user = new User();
        user.setId(UNKNOWN_ID);
        user.setUserStatus(UNKNOWN);
        user.setUserName(UNKNOWN_NAME);
        user.setFirstName(UNKNOWN_NAME);
        user.setLastName(UNKNOWN_NAME);
        user.setTelegramId(UNKNOWN_ID);
        user.setCreatedAt(UNKNOWN_CREATE_DATE);
        user.setUpdatedAt(UNKNOWN_UPDATE_DATE);
        user.setCockSize(getExistingUnknownCockSize(user));
        return user;
    }

    public static UserDto getExistingCockSizeUnknownUserDto() {
        return new UserDto(UNKNOWN_ID, UNKNOWN_NAME, UNKNOWN_NAME, UNKNOWN_NAME, UNKNOWN);
    }

    public static User getUnknownUserWithoutSize() {
        User user = new User();
        user.setId(UNKNOWN_ID);
        user.setUserStatus(UNKNOWN);
        user.setUserName(UNKNOWN_NAME);
        user.setFirstName(UNKNOWN_NAME);
        user.setLastName(UNKNOWN_NAME);
        user.setTelegramId(UNKNOWN_ID);
        user.setCreatedAt(UNKNOWN_CREATE_DATE);
        user.setUpdatedAt(UNKNOWN_UPDATE_DATE);
        return user;
    }

    public static DailyCockSize getExistingUnknownCockSize(User user) {
        return DailyCockSize.builder()
                .user(user)
                .createdAt(UNKNOWN_CREATE_DATE)
                .updatedAt(UNKNOWN_UPDATE_DATE)
                .size(OLD_SIZE)
                .userId(user.getId())
                .build();
    }

    public User getUnknownUserWithoutCock() {
        User user = new User();
        user.setUserStatus(UNKNOWN);
        user.setId(UNKNOWN_ID);
        user.setUserName(UNKNOWN_NAME);
        user.setFirstName(UNKNOWN_NAME);
        return user;
    }
}