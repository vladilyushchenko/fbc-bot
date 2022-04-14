//package com.fbc.bot.telegram.handler.handlers;
//
//import com.fbc.bot.telegram.handler.MessageHandler;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
//
//import static com.fbc.bot.telegram.model.MessageType.INLINE_SHARE_COCK_SIZE;
//
//@Component
//public class InlineCockSizeHandler extends MessageHandler {
//
//    public InlineCockSizeHandler() {
//        super.setMessageType(INLINE_SHARE_COCK_SIZE);
//    }
//
//    @Override
//    public BotApiMethod<?> handleMessage(Update update) {
//        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
//        answerInlineQuery.setInlineQueryId(update.getInlineQuery().getId());
//        answerInlineQuery.setCacheTime(100000);
//        InlineQueryResult
//        answerInlineQuery.setResults();
////        return answerInlineQuery;
//        return null;
//    }
//}