package com.fbc.bot.controller;

import com.fbc.bot.model.User;
import org.junit.jupiter.api.Test;

import static com.fbc.bot.data.UserDataProvider.NON_EXISTING_TELEGRAM_ID;
import static com.fbc.bot.data.UserDataProvider.getNonexistentUserUpdate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BotControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void onChatUpdate_WhenInlineCockSizeWithNewUser_CreatesUserAndCockSize() throws Exception {
        // Given
        String requestBody = getNonexistentUserUpdate();

        // Then
        mvc.perform(post("/")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        User user = userRepository.findByTelegramId(NON_EXISTING_TELEGRAM_ID).orElse(null);
        assertNotNull(user);
        assertNotNull(user.getCockSize());
    }
}