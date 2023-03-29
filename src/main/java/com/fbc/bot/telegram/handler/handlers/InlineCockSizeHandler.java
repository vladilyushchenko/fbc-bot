package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.message.service.LocaleMessageProvider;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.handler.input.MessageType;
import com.fbc.bot.telegram.service.CockSizeServiceFacade;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.List;
import java.util.UUID;

import static com.fbc.bot.telegram.handler.input.MessageType.INLINE_SHARE_COCK_SIZE;
import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_DESCRIPTION_SHARE_COCK_SIZE;
import static com.fbc.bot.util.MessageKeyConstants.Response.INLINE_QUERY_TITLE_SHARE_COCK_SIZE;

@Component
@RequiredArgsConstructor
public class InlineCockSizeHandler implements MessageHandler {

    private final LocaleMessageProvider messageProvider;
    private final CockSizeServiceFacade sizeServiceFacade;

    @Override
    public MessageType getMessageType() {
        return INLINE_SHARE_COCK_SIZE;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        AnswerInlineQuery answer = new AnswerInlineQuery(update.getInlineQuery().getId(), getInlineAnswerList(update));
        answer.setCacheTime(0);
        answer.setIsPersonal(true);
        return answer;
    }

    private List<InlineQueryResult> getInlineAnswerList(Update update) {
        return Lists.newArrayList(getCockSizeArticle(update), getAlcoFriendArticle(update));
    }

    private InlineQueryResultArticle getCockSizeArticle(Update update) {
        InlineQueryResultArticle article = new InlineQueryResultArticle();
        article.setId(UUID.randomUUID().toString());
        article.setTitle(messageProvider.getLocalMessage(INLINE_QUERY_TITLE_SHARE_COCK_SIZE));
        article.setDescription(messageProvider.getLocalMessage(INLINE_QUERY_DESCRIPTION_SHARE_COCK_SIZE));

        InputTextMessageContent content = new InputTextMessageContent();
        content.setMessageText(getCockSizeAnswerMessage(update));
        article.setInputMessageContent(content);
        return article;
    }

    private String getCockSizeAnswerMessage(Update update) {
        var tgUser = update.getInlineQuery().getFrom();
        return sizeServiceFacade.getCockSizeAnswer(tgUser);
    }

    private InlineQueryResultArticle getAlcoFriendArticle(Update update) {
        InlineQueryResultArticle article = new InlineQueryResultArticle();
        article.setId(UUID.randomUUID().toString());
        article.setTitle("С кем можно выпить?");
        article.setDescription("Найди себе друга по выпивке");
        // todo: add pictures

        InputTextMessageContent content = new InputTextMessageContent();
        content.setMessageText("I love vodka");
        article.setInputMessageContent(content);
        // todo: add pictures
        return article;
    }

    private String getAlcoFriendAnswerMessage() {
        throw new UnsupportedOperationException();
    }
}