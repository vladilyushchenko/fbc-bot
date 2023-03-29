package com.fbc.bot.common;

import com.fbc.bot.user.service.UserService;
import com.fbc.bot.user.dto.UserDto;
import com.fbc.bot.user.model.User;
import com.fbc.bot.telegram.service.CockSizeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static com.fbc.bot.data.UserDataProvider.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        assertThat(user)
                .extracting("cockSize.updatedAt")
                .matches(updateDate -> ((OffsetDateTime) updateDate).isAfter(oldCockSizeUpdateDate));
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
        assertThat(user.getCockSize()).isNotNull();
    }
}