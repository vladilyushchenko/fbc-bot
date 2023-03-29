package com.fbc.bot.telegram.handler.handlers;

import com.fbc.bot.message.service.LocaleMessageProvider;
import com.fbc.bot.music.dto.CottageMusicResultDto;
import com.fbc.bot.telegram.handler.MessageHandler;
import com.fbc.bot.telegram.handler.input.MessageType;
import com.fbc.bot.telegram.service.TelegramMusicMessageServiceFacade;
import com.fbc.bot.util.MessageKeyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class PartyMusicMessageHandler implements MessageHandler {

    private final TelegramMusicMessageServiceFacade musicService;
    private final LocaleMessageProvider messageProvider;

    @Override
    public MessageType getMessageType() {
        return MessageType.ADD_MUSIC;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        var result = musicService.create(update.getMessage().getText(), update.getMessage().getFrom());
        String response = messageProvider.getLocalMessage(MessageKeyConstants.Response.ANSWER_ADD_MUSIC_SUCCESS);
        if (Boolean.FALSE.equals(result.success)) {
            response = messageProvider.getLocalMessage(MessageKeyConstants.Response.ANSWER_ADD_MUSIC_FAILURE);
        } else if (CottageMusicResultDto.CreateMusicStatus.ADDED.equals(result.getStatus()) && !result.isNew) {
            response = messageProvider.getLocalMessage(MessageKeyConstants.Response.ANSWER_ADD_MUSIC_ALREADY_EXISTS);
        } else if (CottageMusicResultDto.CreateMusicStatus.WRITE_TITLE_AWAITING.equals(result.getStatus())) {
            response = messageProvider.getLocalMessage(MessageKeyConstants.Response.ANSWER_ADD_MUSIC_WRITE_TITLE);
        }
        return new SendMessage(String.valueOf(update.getMessage().getChatId()), response);
    }
}