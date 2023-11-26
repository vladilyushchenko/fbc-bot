package com.fbc.bot.cocksize.service;

import com.fbc.bot.common.constant.LocaleMessageProvider;
import com.fbc.bot.user.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import static com.fbc.bot.user.model.UserStatus.CLUB_MEMBER;
import static com.fbc.bot.util.MessageKeyConstants.Response.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CockSizeGenerator {

    private final LocaleMessageProvider messageProvider;

    public String generateAnswer(User user) {
        if (CLUB_MEMBER.equals(user.getUserStatus())) {
            final String name = Strings.isBlank(user.getCustomName())
                    ? "@".concat(user.getUserName())
                    : user.getCustomName();
            return getMemberAnswer(name, user.getCockSize().getSize());
        }
        return getUnknownMemberAnswer(user, user.getCockSize().getSize());
    }

    private String getUnknownMemberAnswer(User user, Long cockSize) {
        String name = isNull(user.getUserName())
                ? user.getFirstName()
                : "@".concat(user.getUserName());
        return format(messageProvider.getLocalMessage(INLINE_QUERY_REPLY_DEFAULT_SIZE), name, cockSize);
    }

    private String getMemberAnswer(String nickname, Long cockSize) {
        if (cockSize > 40) {
            return format(messageProvider.getLocalMessage(INLINE_QUERY_REPLY_MEMBER_GOOD_SIZE), nickname, cockSize);
        }
        return format(messageProvider.getLocalMessage(INLINE_QUERY_REPLY_MEMBER_BAD_SIZE), cockSize);
    }
}