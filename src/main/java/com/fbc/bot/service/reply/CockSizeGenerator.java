package com.fbc.bot.service.reply;

import com.fbc.bot.model.User;
import com.fbc.bot.service.message.LocaleMessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.fbc.bot.model.UserStatus.CLUB_MEMBER;
import static com.fbc.bot.util.MessageKeyConstants.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CockSizeGenerator {

    private final LocaleMessageProvider messageProvider;

    public String generateAnswer(User user) {
        if (CLUB_MEMBER.equals(user.getUserStatus())) {
            return getMemberAnswer(user.getNickname(), user.getCockSize().getSize());
        }
        return getUnknownMemberAnswer(user, user.getCockSize().getSize());
    }

    private String getUnknownMemberAnswer(User user, Long cockSize) {
        String name = isNull(user.getUserName())
                ? user.getFirstName()
                : "@" + user.getUserName();
        return format(messageProvider.getLocalMessage(INLINE_QUERY_REPLY_DEFAULT_SIZE), name, cockSize);
    }

    private String getMemberAnswer(String nickname, Long cockSize) {
        if (cockSize > 40) {
            return format(messageProvider.getLocalMessage(INLINE_QUERY_REPLY_MEMBER_GOOD_SIZE), nickname, cockSize);
        }
        return format(messageProvider.getLocalMessage(INLINE_QUERY_REPLY_MEMBER_BAD_SIZE), cockSize);
    }
}