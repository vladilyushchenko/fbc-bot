package com.fbc.bot.service.impl;

import com.fbc.bot.model.User;
import com.fbc.bot.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;

import static com.fbc.bot.data.UserDataProvider.getExistingCockSizeUnknownUser;
import static com.fbc.bot.data.UserDataProvider.getUnknownUserWithoutSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CockSizeServiceImplTest {

    private final UserService userService = Mockito.mock(UserService.class);
    private final CockSizeServiceImpl service = new CockSizeServiceImpl(userService);

    @Test
    public void updateUserCockSize_whenSizeExpired_UpdatesSize() {
        // Given
        User user = getExistingCockSizeUnknownUser();
        OffsetDateTime oldCockSizeUpdateDate = user.getCockSize().getUpdatedAt();

        // When
        when(userService.updateUser(eq(user))).thenReturn(user);

        // Then
        service.updateUserCockSize(user);
        assertTrue(user.getCockSize().getUpdatedAt().isAfter(oldCockSizeUpdateDate));
        verify(userService, times(1)).updateUser(eq(user));
    }

    @Test
    public void updateUserCockSize_whenSizeDoesntExist_CreatesSize() {
        // Given
        User user = getUnknownUserWithoutSize();

        // When
        when(userService.updateUser(eq(user))).thenReturn(user);

        // Then
        service.updateUserCockSize(user);
        assertNotNull(user.getCockSize());
        verify(userService, times(1)).updateUser(eq(user));
    }
}