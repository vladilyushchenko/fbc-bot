package com.fbc.bot.controller;

import com.fbc.bot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.fbc.bot.data.UserDataProvider.getInvalidUserDto;
import static com.fbc.bot.data.UserDataProvider.getValidUserDto;
import static com.fbc.bot.util.JsonMapper.asJsonString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends BaseControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void createUser_WhenUserUnauthorized_Unauthorized() throws Exception {
        mockMvc.perform(post("/api/v1/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void createUser_WhenUserAuthorized_Ok() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .content(asJsonString(getValidUserDto()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void createUser_WhenInvalidRequestBody_BadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .content(asJsonString(getInvalidUserDto()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}