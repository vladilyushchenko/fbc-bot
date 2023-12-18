package com.fbc.bot.telegram.message.handlers;

import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.music.dto.CottageMusicResultDto.CreateMusicStatus;
import com.fbc.bot.telegram.message.input.MessageType;
import com.fbc.bot.telegram.service.TelegramMusicService;
import com.fbc.bot.util.MessageKeyConstants.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class PartyMusicMessageHandler implements MessageHandler {

    private final TelegramMusicService musicService;
    private final LocaleMessageProvider messageProvider;

    @Override
    public MessageType getMessageType() {
        return MessageType.ADD_MUSIC;
    }

    @Override
    public BotApiMethod<?> handleMessage(Update update) {
        var result = musicService.create(update.getMessage().getText(), update.getMessage().getFrom());
        String response = messageProvider.getLocalMessage(Response.ANSWER_ADD_MUSIC_SUCCESS);
        if (Boolean.FALSE.equals(result.getSuccess())) {
            response = messageProvider.getLocalMessage(Response.ANSWER_ADD_MUSIC_FAILURE);
        } else if (CreateMusicStatus.ADDED.equals(result.getStatus()) && !result.getIsNew()) {
            response = messageProvider.getLocalMessage(Response.ANSWER_ADD_MUSIC_ALREADY_EXISTS);
        } else if (CreateMusicStatus.WRITE_TITLE_AWAITING.equals(result.getStatus())) {
            response = messageProvider.getLocalMessage(Response.ANSWER_ADD_MUSIC_WRITE_TITLE);
        }
        return new SendMessage(String.valueOf(update.getMessage().getChatId()), response);
    }
}