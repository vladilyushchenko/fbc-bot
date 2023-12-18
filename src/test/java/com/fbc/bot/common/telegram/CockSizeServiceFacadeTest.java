package com.fbc.bot.common.telegram;

import com.fbc.bot.cocksize.service.CockSizeService;
import com.fbc.bot.cocksize.service.CockSizeServiceFacade;
import com.fbc.bot.common.exception.EntityNotFoundException;
import com.fbc.bot.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fbc.bot.data.TelegramUserProvider.getExistingUser;
import static com.fbc.bot.data.TelegramUserProvider.getUnknownUser;
import static com.fbc.bot.data.UserDataProvider.PREPARED_ANSWER;
import static com.fbc.bot.data.UserDataProvider.getUnknownUserWithoutSize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CockSizeServiceFacadeTest {

    @Mock
    private CockSizeService cockSizeService;
    @Mock
    private UserService userService;
    @InjectMocks
    private CockSizeServiceFacade serviceFacade;

    @Test
    void getCockSizeAnswer_OnNotExistingUser_ReturnsAnswer() {
        // Given
        var tgUser = getUnknownUser();
        var createdUser = getUnknownUserWithoutSize();

        when(userService.getUserByTelegramId(tgUser.getId())).thenThrow(EntityNotFoundException.class);
        when(userService.createUser(tgUser)).thenReturn(createdUser);
        when(cockSizeService.getCockSizeAnswer(createdUser)).thenReturn(PREPARED_ANSWER);

        // When
        String answer = serviceFacade.getCockSizeAnswer(tgUser);

        // Then
        assertThat(answer).isEqualTo(PREPARED_ANSWER);
    }

    @Test
    void getCockSizeAnswer_OnExistingUser_ReturnsAnswer() {
        // Given
        var tgUser = getExistingUser();
        var savedUser = getUnknownUserWithoutSize();

        when(userService.getUserByTelegramId(tgUser.getId())).thenReturn(savedUser);
        when(cockSizeService.getCockSizeAnswer(savedUser)).thenReturn(PREPARED_ANSWER);

        // When
        String answer = serviceFacade.getCockSizeAnswer(tgUser);

        // Then
        assertThat(answer).isEqualTo(PREPARED_ANSWER);
    }
}