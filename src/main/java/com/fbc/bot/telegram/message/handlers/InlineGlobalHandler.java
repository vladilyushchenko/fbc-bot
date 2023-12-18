package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.telegram.message.input.MessageType;
import com.fbc.bot.telegram.message.article.ResultArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;

import java.util.List;
import java.util.stream.Collectors;

import static com.fbc.bot.telegram.message.input.MessageType.INLINE_GLOBAL;

@Component
@RequiredArgsConstructor
public class InlineGlobalHandler implements MessageHandler {

    private final List<ResultArticle> resultArticles;

    @Override
    public MessageType getMessageType() {
        return INLINE_GLOBAL;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        AnswerInlineQuery answer = new AnswerInlineQuery(update.getInlineQuery().getId(),
                getInlineAnswerList(update));
        answer.setCacheTime(0);
        answer.setIsPersonal(true);
        return answer;
    }

    private List<InlineQueryResult> getInlineAnswerList(Update update) {
        return resultArticles.stream()
                .map(el -> el.getArticle(update))
                .collect(Collectors.toList());
    }
}