package com.fbc.bot.telegram.service;

import com.fbc.bot.message.service.LocaleMessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.fbc.bot.telegram.handler.input.IncomeCommand.ASK_ADD_MUSIC;
import static com.fbc.bot.telegram.handler.input.IncomeCommand.ASK_ALEX_RUBCHINSKIY;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class MainMenuService {

    private final LocaleMessageProvider messageProvider;

    public SendMessage getStartMenu(final long chatId, final String textMessage) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard();
        return createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
    }

    private ReplyKeyboardMarkup getMainMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = getMainKeyboard();
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    private List<KeyboardRow> getMainKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();
//        KeyboardRow joinPartyRow = new KeyboardRow();
//        KeyboardRow cockSizeRow = new KeyboardRow();
        KeyboardRow alexRubchinskiyRow = new KeyboardRow();
        KeyboardRow addMusicRow = new KeyboardRow();
//        joinPartyRow.add(new KeyboardButton(messageProvider.getLocalMessage(ASK_JOIN_PARTY.getKey())));
//        cockSizeRow.add(new KeyboardButton(messageProvider.getLocalMessage(ASK_SHARE_COCK_SIZE.getKey())));
        alexRubchinskiyRow.add(new KeyboardButton(messageProvider.getLocalMessage(ASK_ALEX_RUBCHINSKIY.getKey())));
        addMusicRow.add(new KeyboardButton(messageProvider.getLocalMessage(ASK_ADD_MUSIC.getKey())));
//        keyboard.add(joinPartyRow);
//        keyboard.add(cockSizeRow);
        keyboard.add(alexRubchinskiyRow);
        keyboard.add(addMusicRow);
        return keyboard;
    }

    private SendMessage createMessageWithKeyboard(final long chatId,
                                                  String textMessage,
                                                  ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textMessage);
        if (nonNull(replyKeyboardMarkup)) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }
}