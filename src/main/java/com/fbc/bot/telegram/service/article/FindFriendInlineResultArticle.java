package com.fbc.bot.telegram.service.article;

import com.fbc.bot.user.service.FindFriendTextGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindFriendInlineResultArticle implements ResultArticle {

    private final FindFriendTextGenerator findFriendTextGenerator;

    @Override
    public InlineQueryResultArticle getArticle(Update update) {
        InlineQueryResultArticle article = new InlineQueryResultArticle();
        article.setId(UUID.randomUUID().toString());
        article.setTitle("С кем мне выпить?");
        article.setDescription("Найти себе друга для совместного времяпрепровождения на баре");
        // todo: add pictures

        InputTextMessageContent content = new InputTextMessageContent();
        String response = findFriendTextGenerator.getText(update.getInlineQuery().getFrom().getId());
        content.setMessageText(response);
        article.setInputMessageContent(content);
        // todo: add pictures
        return article;
    }
}