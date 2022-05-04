package com.fbc.bot.service.telegram;

import com.fbc.bot.service.message.LocaleMessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.fbc.bot.telegram.model.IncomeCommand.*;
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
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        row1.add(new KeyboardButton(messageProvider.getLocalMessage(ASK_JOIN_PARTY.getKey())));
        row2.add(new KeyboardButton(messageProvider.getLocalMessage(ASK_SHARE_COCK_SIZE.getKey())));
        row3.add(new KeyboardButton(messageProvider.getLocalMessage(ASK_ALEX_RUBCHINSKIY.getKey())));
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
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