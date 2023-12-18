package com.fbc.bot.telegram.message.article;

import com.fbc.bot.cocksize.service.CockSizeServiceFacade;
import com.fbc.bot.common.config.properties.TelegramProperties;
import com.fbc.bot.common.constant.LocaleMessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.UUID;

import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_DESCRIPTION_SHARE_COCK_SIZE;
import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_TITLE_SHARE_COCK_SIZE;

@Component
@RequiredArgsConstructor
public class CockSizeInlineResultArticle implements ResultArticle {

    private final LocaleMessageProvider messageProvider;
    private final CockSizeServiceFacade sizeServiceFacade;
    private final TelegramProperties telegramProperties;

    @Override
    public InlineQueryResultArticle getArticle(Update update) {
        InlineQueryResultArticle article = new InlineQueryResultArticle();
        article.setId(UUID.randomUUID().toString());
        article.setTitle(messageProvider.getLocalMessage(INLINE_QUERY_TITLE_SHARE_COCK_SIZE));
        article.setDescription(messageProvider.getLocalMessage(INLINE_QUERY_DESCRIPTION_SHARE_COCK_SIZE));
        article.setThumbUrl(telegramProperties.getBot().getInlinePhotoUrl());

        InputTextMessageContent content = new InputTextMessageContent();
        content.setMessageText(getCockSizeAnswerMessage(update));
        article.setInputMessageContent(content);
        return article;
    }

    private String getCockSizeAnswerMessage(Update update) {
        var tgUser = update.getInlineQuery().getFrom();
        return sizeServiceFacade.getCockSizeAnswer(tgUser);
    }
}
