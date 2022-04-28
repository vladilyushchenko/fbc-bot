package com.fbc.bot.service.impl;

import com.fbc.bot.dto.UserDto;
import com.fbc.bot.model.User;
import com.fbc.bot.service.CockSizeService;
import com.fbc.bot.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static com.fbc.bot.data.UserDataProvider.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CockSizeServiceTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private CockSizeService service;

    @Test
    void updateUserCockSize_whenSizeExpired_UpdatesSize() {
        // Given
        User user = getExistingCockSizeUnknownUser();
        UserDto userDto = getExistingCockSizeUnknownUserDto();
        OffsetDateTime oldCockSizeUpdateDate = user.getCockSize().getUpdatedAt();

        when(userService.updateUser(user)).thenReturn(userDto);

        // When
        service.updateUserCockSize(user);

        // Then
        assertTrue(user.getCockSize().getUpdatedAt().isAfter(oldCockSizeUpdateDate));
    }

    @Test
    void updateUserCockSize_whenSizeDoesntExist_CreatesSize() {
        // Given
        User user = getUnknownUserWithoutSize();
        UserDto userDto = getExistingCockSizeUnknownUserDto();

        when(userService.updateUser(user)).thenReturn(userDto);

        // When
        service.updateUserCockSize(user);

        // Then
        assertNotNull(user.getCockSize());
    }
}