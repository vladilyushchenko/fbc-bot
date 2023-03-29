package com.fbc.bot.telegram.service.article;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.UUID;

@Component
public class AlcoholFriendInlineResultArticle implements ResultArticle {
    @Override
    public InlineQueryResultArticle getArticle(Update update) {
        InlineQueryResultArticle article = new InlineQueryResultArticle();
        article.setId(UUID.randomUUID().toString());
        // todo error: unmappable character (0xD1) for encoding UTF-8
        article.setTitle("Who can I have a drink with?");
        article.setDescription("Find yourself a drinking friend");
        // todo: add pictures

        InputTextMessageContent content = new InputTextMessageContent();
        content.setMessageText("I love vodka");
        article.setInputMessageContent(content);
        // todo: add pictures
        return article;
    }
}
