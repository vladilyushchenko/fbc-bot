package com.fbc.bot.base;

import com.fbc.bot.FbcBotApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = FbcBotApplication.class)
public abstract class BaseIntegrationTest {
}