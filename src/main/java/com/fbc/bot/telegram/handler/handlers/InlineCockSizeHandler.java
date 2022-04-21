package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.model.User;
import com.fbc.bot.service.CockSizeService;
import com.fbc.bot.service.UserService;
import com.fbc.bot.service.message.LocaleMessageProvider;
import com.fbc.bot.service.reply.CockSizeGenerator;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import java.util.Optional;

import static com.fbc.bot.telegram.model.MessageType.INLINE_SHARE_COCK_SIZE;
import static com.fbc.bot.util.MessageKeyConstants.INLINE_QUERY_DESCRIPTION_SHARE_COCK_SIZE;
import static com.fbc.bot.util.MessageKeyConstants.INLINE_QUERY_TITLE_SHARE_COCK_SIZE;

@Component
@RequiredArgsConstructor
public class InlineCockSizeHandler implements MessageHandler {

    private final CockSizeService sizeService;
    private final UserService userService;
    private final CockSizeGenerator sizeGenerator;
    private final LocaleMessageProvider messageProvider;

    @Override
    public MessageType getMessageType() {
        return INLINE_SHARE_COCK_SIZE;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        return getAnswerInlineQuery(update);
    }

    private AnswerInlineQuery getAnswerInlineQuery(Update update) {
        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
        answerInlineQuery.setInlineQueryId(update.getInlineQuery().getId());
        answerInlineQuery.setResults(getInlineAnswer(update));
        answerInlineQuery.setCacheTime(0);
        answerInlineQuery.setPersonal(true);
        return answerInlineQuery;
    }

    private InlineQueryResult getInlineAnswer(Update update) {
        InputTextMessageContent messageContent = new InputTextMessageContent();
        messageContent.setMessageText(getAnswerMessage(update));
        return getResultArticle(update.getInlineQuery(), messageContent);
    }

    private InlineQueryResultArticle getResultArticle(InlineQuery query, InputMessageContent content) {
        InlineQueryResultArticle article = new InlineQueryResultArticle();
        article.setId(query.getId());
        article.setTitle(messageProvider.getLocalMessage(INLINE_QUERY_TITLE_SHARE_COCK_SIZE));
        article.setDescription(messageProvider.getLocalMessage(INLINE_QUERY_DESCRIPTION_SHARE_COCK_SIZE));
        article.setInputMessageContent(content);
        return article;
    }

    private String getAnswerMessage(Update update) {
        var tgUser = update.getInlineQuery().getFrom();
        Optional<User> user = userService.getUserByTelegramId(tgUser.getId());
        if (user.isEmpty()) {
            user = Optional.of(userService.createUser(tgUser));
        }
        sizeService.updateUserCockSize(user.get());
        return sizeGenerator.generateAnswer(user.get());
    }
}