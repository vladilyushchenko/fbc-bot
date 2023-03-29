package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.base.BaseIntegrationTest;

class CockSizeMessageHandlerIntegrationTest extends BaseIntegrationTest {

    /*@Autowired
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
        assertThat(cockSizeContent)
                .extracting(InputTextMessageContent::getMessageText)
                .matches(Strings::isNotBlank);
    }*/
}