package com.fbc.bot.telegram.message.article;

import com.fbc.bot.common.config.properties.TelegramProperties;
import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.user.service.FindFriendTextGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.UUID;

import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_DESCRIPTION_FIND_FRIEND;
import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_TITLE_FIND_FRIEND;

@Component
@RequiredArgsConstructor
public class FindFriendInlineResultArticle implements ResultArticle {

    private final FindFriendTextGenerator findFriendTextGenerator;
    private final LocaleMessageProvider localeMessageProvider;
    private final TelegramProperties telegramProperties;

    @Override
    public InlineQueryResultArticle getArticle(Update update) {
        InlineQueryResultArticle article = new InlineQueryResultArticle();
        article.setId(UUID.randomUUID().toString());
        article.setTitle(localeMessageProvider.getLocalMessage(INLINE_QUERY_TITLE_FIND_FRIEND));
        article.setDescription(localeMessageProvider.getLocalMessage(INLINE_QUERY_DESCRIPTION_FIND_FRIEND));
        article.setThumbUrl(telegramProperties.getBot().getInlinePhotoUrl());

        InputTextMessageContent content = new InputTextMessageContent();
        String response = findFriendTextGenerator.getText(update.getInlineQuery().getFrom().getId());
        content.setMessageText(response);
        article.setInputMessageContent(content);

        return article;
    }
}