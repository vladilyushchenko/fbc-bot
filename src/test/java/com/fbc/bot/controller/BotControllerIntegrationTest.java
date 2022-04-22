package com.fbc.bot.controller;

import com.fbc.bot.base.BaseIntegrationTest;
import com.fbc.bot.service.BotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class BotControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BotService botService;

    @Test
    public void onUpdate_GetsRequests() throws Exception {
        mockMvc.perform(post("/")
                        .contentType(APPLICATION_JSON)
                        .content("{\"updateId\":1}"))
                .andExpect(status().isOk());
    }
}