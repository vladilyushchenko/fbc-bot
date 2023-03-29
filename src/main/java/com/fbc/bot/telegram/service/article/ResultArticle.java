package com.fbc.bot.telegram.service.article;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

public interface ResultArticle {
    InlineQueryResultArticle getArticle(Update update);
}
