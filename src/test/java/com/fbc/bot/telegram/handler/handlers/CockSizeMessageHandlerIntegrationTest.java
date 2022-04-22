package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.base.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import static com.fbc.bot.data.UpdateDataProvider.COCK_SIZE_INDEX;
import static com.fbc.bot.data.UpdateDataProvider.getUpdateWithNonExistingUser;
import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CockSizeMessageHandlerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private InlineCockSizeHandler handler;

    @Test
    void handleMessage_WhenInlineCockSizeWithNewUser_CreatesUserAndCockSize() {
        // Given
        Update update = getUpdateWithNonExistingUser();

        // When
        BotApiMethod<?> result = handler.handleMessage(update);

        // Then
        var inlineAnswer = (AnswerInlineQuery) result;
        var content = (InlineQueryResultArticle) inlineAnswer.getResults().get(COCK_SIZE_INDEX);
        var cockSizeContent = (InputTextMessageContent) content.getInputMessageContent();
        assertTrue(isNotBlank(cockSizeContent.getMessageText()));
    }
}